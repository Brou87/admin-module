/**
MIT License

Copyright (c) 2021 Surati

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package io.surati.gap.admin.module.web.actions;

import io.surati.gap.admin.module.api.Log;
import io.surati.gap.admin.module.api.User;
import io.surati.gap.admin.module.api.Users;
import io.surati.gap.admin.module.codec.GIdentity;
import io.surati.gap.admin.module.db.DbLog;
import io.surati.gap.admin.module.db.DbUsers;
import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.auth.Identity;
import org.takes.facets.auth.Pass;
import org.takes.facets.flash.RsFlash;
import org.takes.facets.forward.RsForward;
import org.takes.rq.RqGreedy;
import org.takes.rq.form.RqFormSmart;

import javax.sql.DataSource;

/**
 * Take that authenticates an user.
 *
 * <p>The class is immutable and thread-safe.</p>
 *
 * @since 0.1
 */
public final class TkAuthenticate implements Take {

	/**
	 * Database
	 */
	private final DataSource source;
	
	private final Pass pass;
	
	/**
	 * Ctor.
	 * @param source DataSource
	 */
	public TkAuthenticate(final DataSource source, final Pass pass) {
		this.source = source;
		this.pass = pass;
	}
	
	@Override
	public Response act(Request req) throws Exception {
		final Log log = new DbLog(this.source, req);
		final RqFormSmart form = new RqFormSmart(new RqGreedy(req));		
		final String login = form.single("login");
		final String password = form.single("password");
		final Users users = new DbUsers(this.source);
		if(users.authenticate(login, password)) {
			final User user = users.get(login);
			final Identity idt = new GIdentity(user);
			log.info("L'utilisateur %s s'est connecté avec succès !", login);
			return pass.exit(
				new RsForward(
					new RsFlash(
						String.format("Bienvenue %s !", user.name())								
					),
					"/home"
				), 
				idt
			);
		} else {
			throw new IllegalArgumentException("Le login ou le mot de passe est invalide !");
		}		
	}

}
