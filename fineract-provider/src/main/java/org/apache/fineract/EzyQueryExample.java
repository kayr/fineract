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
