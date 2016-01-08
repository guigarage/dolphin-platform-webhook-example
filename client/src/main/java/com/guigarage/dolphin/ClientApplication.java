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
package com.guigarage.dolphin;

import com.canoo.dolphin.client.ClientConfiguration;
import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.ClientContextFactory;
import com.canoo.dolphin.client.javafx.JavaFXConfiguration;
import com.guigarage.dolphin.view.ToDoView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    private ClientContext clientContext;

    @Override
    public void init() throws Exception {
        ClientConfiguration config = new JavaFXConfiguration("http://localhost:8080/dolphin");
        clientContext = ClientContextFactory.connect(config).get();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new ToDoView(clientContext).getPane());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
