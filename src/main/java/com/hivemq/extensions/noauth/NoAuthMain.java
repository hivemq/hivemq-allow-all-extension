/*
 * Copyright 2020 dc-square GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extensions.noauth;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.auth.SimpleAuthenticator;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Brandl
 */
public class NoAuthMain implements ExtensionMain {

    private static final @NotNull Logger log = LoggerFactory.getLogger(NoAuthMain.class);
    private static final @NotNull SimpleAuthenticator ALLOW_ALL_AUTHENTICATOR = new AllowAllAuthenticator();

    @Override
    public void extensionStart(final @NotNull ExtensionStartInput extensionStartInput, final @NotNull ExtensionStartOutput extensionStartOutput) {

        try {
            log.warn("\n#####################################################################################################" +
                    "\n# This is an insecure deployment. Every MQTT client is fully authorized.                            #" +
                    "\n# For production usage add an authentication extension and remove the hivemq-no-auth extension.     #" +
                    "\n# Authentication extensions can be found in the marketplace (https://www.hivemq.com/extensions/).   #" +
                    "\n#####################################################################################################");
            Services.securityRegistry().setAuthenticatorProvider(authenticatorProviderInput -> ALLOW_ALL_AUTHENTICATOR);

        } catch (final Exception e) {
            log.error("Exception thrown at extension start: ", e);
        }
    }

    @Override
    public void extensionStop(final @NotNull ExtensionStopInput extensionStopInput, final @NotNull ExtensionStopOutput extensionStopOutput) {
    }
}
