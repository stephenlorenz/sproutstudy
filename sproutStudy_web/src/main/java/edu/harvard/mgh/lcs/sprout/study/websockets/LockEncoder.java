/*
 * Copyright 2015 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package edu.harvard.mgh.lcs.sprout.study.websockets;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import org.atmosphere.config.managed.Encoder;

import java.io.IOException;

/**
 * Encode a {@link LockTO} into a String
 */
public class LockEncoder implements Encoder<LockTO, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(LockTO lockTO) {
        try {
            return mapper.writeValueAsString(lockTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
