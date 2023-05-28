/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract;

import io.github.kayr.ezyquery.EzySql;
import io.github.kayr.ezyquery.api.cnd.Cnd;
import lombok.RequiredArgsConstructor;
import org.apache.fineract.queries.customers.AuditExample;
import org.springframework.stereotype.Service;

import static org.apache.fineract.queries.customers.AuditExample.ACTION_NAME;
import static org.apache.fineract.queries.customers.AuditExample.ENTITY_NAME;

@Service
@RequiredArgsConstructor
public class EzyQueryExample {

    private final EzySql ezySql;

    /*
     * Useful for simple queries but sometimes you might not easily be able to vision the precedence of the operators
     */
    public void listAndCountUsingTheFluentAPI() {
        ezySql.from(AuditExample.QUERY).where(
          ACTION_NAME.eq("create")
            .and(ENTITY_NAME.eq("client")
                   .or(ENTITY_NAME.eq("loan"))))
          .orderBy(AuditExample.MADE_ON_DATE.desc(), AuditExample.ID.desc()).limit(10, 0).listAndCount();

    }

    /**
     * if you want direct control over the precedence of the operators the Cnd API is much more readable
     */
    public void listAndCountCndApi() {
        ezySql.from(AuditExample.QUERY)
          .where(Cnd.and(
            Cnd.eq(ACTION_NAME, "create"),
            Cnd.or(
              Cnd.eq(ENTITY_NAME, "client"),
              Cnd.eq(ENTITY_NAME, "loan")
            )))
          .orderBy(AuditExample.MADE_ON_DATE.desc(), AuditExample.ID.desc()).limit(10, 0).listAndCount();

    }

    public void count() {
        ezySql.from(AuditExample.QUERY).where(ACTION_NAME.eq("create").and(ENTITY_NAME.eq("client").or(ENTITY_NAME.eq("loan")))).count();

    }

    public void list() {
        ezySql.from(AuditExample.QUERY).where(ACTION_NAME.eq("create").and(ENTITY_NAME.eq("client").or(ENTITY_NAME.eq("loan"))))
          .orderBy(AuditExample.MADE_ON_DATE.desc(), AuditExample.ID.desc()).list();

    }

}
