/*
 * Copyright (c) 2022 Surati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.surati.gap.admin.module.jooq;

import com.lightweight.db.EmbeddedPostgreSQLDataSource;
import com.lightweight.db.LiquibaseDataSource;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;

/**
 * Class used to generate jOOQ classes.
 * @since 0.1
 */
public final class JooqGen {

    /**
     * Entry point.
     * @param args Arguments
     */
    public static void main(final String[] args) throws Exception {
        new LiquibaseDataSource(
            new EmbeddedPostgreSQLDataSource("db_test"),
            "liquibase/db-admin.changelog-master.xml"
        ).getConnection().close();
        GenerationTool.generate(
            new Configuration()
                .withJdbc(
                    new Jdbc()
                        .withDriver("org.h2.Driver")
                        .withUrl("jdbc:h2:~/db_test")
                ).withGenerator(
                    new Generator()
                        .withDatabase(
                            new Database()
                                .withName("org.jooq.meta.h2.H2Database")
                                .withIncludes(".*")
                                .withExcludes("databasechangelog|databasechangeloglock")
                                .withInputSchema("public"))
                        .withTarget(
                            new Target()
                                .withPackageName("io.surati.gap.admin.module.jooq.generated")
                                .withDirectory("src/main/java")
                        )
                )
        );
    }
}
