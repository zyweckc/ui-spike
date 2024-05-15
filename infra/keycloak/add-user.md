# Add a user to the realm-export

After creating a new export, you can add a user to the realm-export.
This is necessary because users are not exported when creating a realm-export.

Simply add this snippet before the final curly brace

```json
"users": [
    {
        "createdTimestamp": 1714557600,
        "username": "test-user",
        "email": "test-user@gmail.com",
        "emailVerified": true,
        "firstName": "test",
        "lastName": "user",
        "enabled": true,
        "credentials": [
            {
                "type": "password",
                "value": "password",
                "temporary": false
            }
        ],
        "clientRoles": {
            "account": [
                "manage-account",
                "manage-account-links",
                "view-profile"
            ]
        },
        "realmRoles": [
            "offline_access",
            "default-roles-ui-spike",
            "uma_authorization",
            "user"
        ]
    }
]
```
