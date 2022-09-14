package LiveProject;

import au.com.dius.pact.provider.PactVerification;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.HttpsTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.security.PublicKey;

@Provider("UserProvider")
@PactFolder("target/pacts")

public class ProviderTest {
    @BeforeEach
    public void setUp(PactVerificationContext context){
        HttpTestTarget target = new HttpsTestTarget("localhost",8585);
        context.setTarget(target);
    }

        @TestTemplate
        @ExtendWith(PactVerificationInvocationContextProvider.class)
        public void providerTest(PactVerificationContext context) {
            context.verifyInteraction();
        }
        @State("A request to create a user")
        public void state1() {}


}
