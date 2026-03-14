package input;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;

import global.Config;

public class KeyMap {
    InputMap inputMap;
    ActionMap actionMap;

    Queue<InputEvent> eventQueue;

    public KeyMap(JComponent window) {
        this.eventQueue = new ConcurrentLinkedQueue<>();
        this.inputMap = window.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.actionMap = window.getActionMap();
    }

    public void initKeyBind() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        long nanoTime = System.nanoTime();

                        int eventID = e.getID();
                        if (eventID != KeyEvent.KEY_PRESSED && eventID != KeyEvent.KEY_RELEASED)
                            return false;

                        String key = KeyEvent.getKeyText(e.getKeyCode());

                        for (int i = 0; i < Config.keyMap.size(); i++) {
                            if (Config.keyMap.get(i).containsKey(key)) {
                                eventQueue.add(new InputEvent(
                                        nanoTime, key, eventID == KeyEvent.KEY_PRESSED,
                                        i, Config.keyMap.get(i).get(key)));
                                return true;
                            }
                        }
                        return false;
                    }
                });
    }

    public Queue<InputEvent> getQueue() {
        return eventQueue;
    }
}
