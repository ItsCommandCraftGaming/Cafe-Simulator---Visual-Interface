package proiect;

import java.io.IOException;
import javafx.scene.layout.AnchorPane;

public abstract class TranzitieFerestre {
    public abstract <T> T schimbareWindow(String cale, AnchorPane fereastraNoua, String titluScena) throws IOException;

}