/*
 * Copyright 2016 GuiGarage (www.guigarage.com).
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
package com.guigarage.dolphin.webhook;

import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.dolphin.ServerConstants;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/todo")
public class WebhookController {

    @Inject
    private DolphinEventBus eventBus;

    @RequestMapping(method = RequestMethod.POST)
    void add(@RequestParam("name") String todoName) {
        eventBus.publish(ServerConstants.TODO_ADDED, todoName);
    }
}
