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
package com.guigarage.dolphin.controller;

import com.canoo.dolphin.server.DolphinAction;
import com.canoo.dolphin.server.DolphinController;
import com.canoo.dolphin.server.DolphinModel;
import com.canoo.dolphin.server.Param;
import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.ServerConstants;
import com.guigarage.dolphin.model.ToDoModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@DolphinController(Constants.TODO_CONTROLLER_NAME)
public class ToDoController {

    @DolphinModel
    private ToDoModel model;

    @Inject
    private DolphinEventBus eventBus;

    @PostConstruct
    public void init() {
        eventBus.subscribe(ServerConstants.TODO_ADDED, e -> model.getTodos().add(e.getData()));
    }

    @DolphinAction(Constants.CLEAR_ACTION)
    public void onClear() {
        model.getTodos().clearContent();
    }

    @DolphinAction(Constants.REMOVE_ACTION)
    public void onRemove(@Param(Constants.NAME_PARAM) String name) {
        if(name != null) {
            model.getTodos().remove(name);
        }
    }
}
