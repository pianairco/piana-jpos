package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.data.database.QPQueryFactory;
import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.authorize.QPHttpAuthorizationType;
import org.jdom2.Element;

import java.util.*;

import static ir.piana.dev.secure.util.Base64Converter.toBase64String;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPIdentityProviderFactory {
    public static QPIdentityProvider createIdentityProvider(
            Element element)
            throws QPException {
        QPIdentityManagementType identityManagementType = QPIdentityManagementType
                .fromCode(element.getAttributeValue(
                        "qp-identity-management-type",
                        "database"));
        switch (identityManagementType) {
            case IN_MEMORY:
                return createInMemoryIdentityProvider(
                        element);
            case DATABASE:
                return createDatabaseIdentityProvider(
                        element);
        }
        throw new QPException("identity provider type not support");
    }

    protected static QPIdentityProvider createInMemoryIdentityProvider(
            Element element) throws QPException {
        String authorizationTypeCode = element
                .getChildText("qp-authorization-provider-type");
        QPHttpAuthorizationType httpAuthorizationType = QPHttpAuthorizationType
                .fromCode(authorizationTypeCode);
        List<Element> userElements = element.getChildren("qp-user");
        final Map<String, String> userToCredentialMap = new LinkedHashMap<>();
        for (Element userElement : userElements) {
            String username = userElement.getChildText("username");
            String password = userElement.getChildText("password");
            String identity = userElement.getChildText("identity");

            List<String> roleList = new ArrayList<>();
            if(username != null && !username.isEmpty() &&
                    password != null && !password.isEmpty() &&
                    identity != null && !identity.isEmpty()){
                String b64user = toBase64String(
                        username.concat(":").concat(password).getBytes());
                userToCredentialMap.put("Basic ".concat(b64user), identity);
            }
        }
        return new QPInMemoryIdentityProvider(httpAuthorizationType,
                Collections.unmodifiableMap(userToCredentialMap));
    }

    protected static QPIdentityProvider createDatabaseIdentityProvider(
            Element identityProviderConfig) throws QPException {
        String databaseManagerName = identityProviderConfig
                .getChildText("qp-database-manager");
        String databaseInstanceName = identityProviderConfig
                .getChildText("qp-database-instance");
        Element userQueryElement = identityProviderConfig.getChild("qp-user-query");

        QPQueryStruct queryStruct = QPQueryFactory.createQueryStruct(
                identityProviderConfig.getChild("qp-identity-query"));

//        QPDatabaseManagerModule databaseManagerModule = QPBaseModule
//                .getModule(databaseManagerName);

//        List<String> roleList = new ArrayList<>();
//        if(username != null && !username.isEmpty() &&
//                password != null && !password.isEmpty() &&
//                identity != null && !identity.isEmpty()){
//            String b64user = toBase64String(
//                    username.concat(":").concat(password).getBytes());
//            userToCredentialMap.put("Basic ".concat(b64user), identity);
//        }

        return new QPDatabaseIdentityProvider(
                databaseManagerName,
                databaseInstanceName,
                queryStruct);
    }
}
