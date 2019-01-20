package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.role.InMemoryRoleManager;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManager;
import ir.piana.dev.jpos.qp.core.security.role.RoleManagementType;
import org.jdom2.Element;

import java.util.*;

import static ir.piana.dev.secure.util.Base64Converter.*;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class IdentityProviderFactory {
    public static QPIdentityProvider createIdentityProvider(
            IdentityManagementType identityManagementType,
            Element element)
            throws QPException {
        switch (identityManagementType) {
            case IN_MEMORY:
                return createInMemoryIdentityProvider(
                        element.getChildren("qp-user"));
            case DATABASE:
                break;
        }
        throw new QPException("identity provider type not support");
    }

    protected static QPIdentityProvider createInMemoryIdentityProvider(
            List<Element> userElements) {
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
        return new InMemoryIdentityProvider(
                Collections.unmodifiableMap(userToCredentialMap));
    }
}
