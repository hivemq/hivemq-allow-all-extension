/*
 * Copyright 2020-present HiveMQ GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hivemq.extensions.allowall;

import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.auth.SimpleAuthenticator;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Brandl
 */
@SuppressWarnings("unused")
public class AllowAllExtensionMain implements ExtensionMain {

    private static final @NotNull Logger log = LoggerFactory.getLogger(AllowAllExtensionMain.class);
    private static final @NotNull SimpleAuthenticator ALLOW_ALL_AUTHENTICATOR = new AllowAllAuthenticator();

    @Override
    public void extensionStart(final @NotNull ExtensionStartInput input, final @NotNull ExtensionStartOutput output) {
        try {
            log.warn(
                    "\n################################################################################################################" +
                            "\n# This HiveMQ deployment is not secure! You are lacking Authentication and Authorization.                      #" +
                            "\n# Right now any MQTT client can connect to the broker with a full set of permissions.                          #" +
                            "\n# For production usage, add an appropriate security extension and remove the hivemq-allow-all extension.       #" +
                            "\n# You can download security extensions from the HiveMQ Marketplace (https://www.hivemq.com/extensions/).       #" +
                            "\n################################################################################################################");
            Services.securityRegistry().setAuthenticatorProvider(authenticatorProviderInput -> ALLOW_ALL_AUTHENTICATOR);

        } catch (final Exception e) {
            log.error("Exception thrown at extension start: ", e);
        }
    }

    @Override
    public void extensionStop(final @NotNull ExtensionStopInput input, final @NotNull ExtensionStopOutput output) {
    }
}
