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
package com.guigarage.dolphin.view;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.Param;
import com.canoo.dolphin.client.javafx.AbstractViewBinder;
import com.canoo.dolphin.client.javafx.FXBinder;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.model.ToDoModel;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class ToDoView extends AbstractViewBinder<ToDoModel> {

    private BorderPane pane;

    private ListView<String> todoList;

    private Button resetButton;

    private Button removeButton;

    public ToDoView(ClientContext clientContext) {
        super(clientContext, Constants.TODO_CONTROLLER_NAME);

        //Define the layout
        todoList = new ListView<>();
        resetButton = new Button("reset");
        removeButton = new Button("remove");
        ToolBar toolBar = new ToolBar(removeButton, resetButton);
        pane = new BorderPane();
        pane.setCenter(todoList);
        pane.setTop(toolBar);
    }

    @Override
    protected void init() {

        //Define the event handlers
        resetButton.setOnAction(e -> invoke(Constants.CLEAR_ACTION));
        removeButton.setOnAction(e -> {
            Param param = new Param(Constants.NAME_PARAM, todoList.getSelectionModel().getSelectedItem());
            invoke(Constants.REMOVE_ACTION, param);
        });

        //Define the Binding
        FXBinder.bind(todoList.getItems()).to(getModel().getTodos());
    }

    public BorderPane getPane() {
        return pane;
    }
}
