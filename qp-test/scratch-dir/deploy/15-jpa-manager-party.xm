<qbean name="qp-jpa-manager-bazar"
       class="ir.piana.dev.jpos.qp.spring.module.QPJpaManagerModule"
       logger="Q2">
    <!--<property name="qp-space" value="tspace:default" />-->
    <!--<property name="qp-in" value="qp-http-request-multiplexor-module-in" />-->

    <datasource name="datasource-party">
        <database-platform>mysql</database-platform>
        <jdbc-url>jdbc:mysql://localhost:3306/party_db</jdbc-url>
        <driver-class-name>com.mysql.cj.jdbc.Driver</driver-class-name>
        <user>root</user>
        <password></password>
        <sid></sid>
        <pool-size>100</pool-size>
    </datasource>

    <entity-manager-factory name="entity-manager-factory-party">
        <database-platform>mysql</database-platform>
        <jpa-provider>eclipselink</jpa-provider>
        <persistence-unit-name>party-pu</persistence-unit-name>
        <base-package>ir.piana.jpos.test.party.entity</base-package>
    </entity-manager-factory>

    <!--<datasource name="datasource-party">-->
        <!--<database-platform>derby</database-platform>-->
        <!--<jdbc-url>jdbc:derby:party_db;create=true</jdbc-url>-->
        <!--<driver-class-name>org.apache.derby.jdbc.EmbeddedDriver</driver-class-name>-->
        <!--<user></user>-->
        <!--<password></password>-->
        <!--<sid></sid>-->
        <!--<pool-size>100</pool-size>-->
    <!--</datasource>-->

    <!--<entity-manager-factory name="entity-manager-factory-party">-->
        <!--<database-platform>derby</database-platform>-->
        <!--<jpa-provider>eclipselink</jpa-provider>-->
        <!--<persistence-unit-name>party-pu</persistence-unit-name>-->
        <!--<base-package>ir.piana.jpos.test.party.entity</base-package>-->
    <!--</entity-manager-factory>-->


    <!--<qp-identity-management-type>in-memory</qp-identity-management-type>-->
    <!--<qp-authorization-provider-type>basic</qp-authorization-provider-type>-->
    <!--<qp-user>-->
        <!--<username>user1</username>-->
        <!--<password>123</password>-->
        <!--<identity>user1</identity>-->
    <!--</qp-user>-->
    <!--<qp-user>-->
        <!--<username>user2</username>-->
        <!--<password>321</password>-->
        <!--<identity>user2</identity>-->
    <!--</qp-user>-->

</qbean>