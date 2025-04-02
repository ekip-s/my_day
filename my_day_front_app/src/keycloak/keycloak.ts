import Keycloak from 'keycloak-js';

const keycloakConfig = {
    url: import.meta.env.VITE_KEYCLOAK_URL,
    realm: 'my_day',
    clientId: 'my_day_react_app',
};

const keycloak = new Keycloak(keycloakConfig);

export default keycloak;