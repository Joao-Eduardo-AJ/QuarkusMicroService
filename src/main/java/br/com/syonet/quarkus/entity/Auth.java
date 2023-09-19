package br.com.syonet.quarkus.entity;

public class Auth extends ErrorMessageDefault {
    private AuthCredentials authCredentials;

    public Auth(AuthCredentials authCredentials) {
        this.authCredentials = authCredentials;
    }

    public static class AuthCredentials {
        private String token;
        private String type;

        public String getToken() {
            return token;
        }
    
        public String getType() {
            return type;
        }
    }

    public AuthCredentials getAuthCredentials() {
        return authCredentials;
    }
}