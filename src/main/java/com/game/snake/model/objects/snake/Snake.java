package com.game.snake.model.objects.snake;

import com.game.snake.model.objects.room.Room;
import com.game.snake.model.setting.Setting;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koliadin Nikita
 * @version 1.13
 */
public final class Snake {

    @Getter private final List<SnakeSection> sections = new ArrayList<>();

    @Getter @Setter private SnakeDirection direction;

    @Getter private boolean alive;

    public Snake() {
        sections.add(new SnakeSection(1, 1));
        direction = SnakeDirection.DOWN;
        alive = true;
    }

    public int getHeadX() {
        return sections.get(0).getX();
    }

    public int getHeadY() {
        return sections.get(0).getY();
    }

    public void move() {
        if (!alive) {
            return;
        }

        if (direction == SnakeDirection.UP) {
            move(0, -1);
        } else if (direction == SnakeDirection.RIGHT) {
            move(1, 0);
        } else if (direction == SnakeDirection.DOWN) {
            move(0, 1);
        } else if (direction == SnakeDirection.LEFT) {
            move(-1, 0);
        }
    }

    private void move(final int dx, final int dy) {
        val head = new SnakeSection(getHeadX() + dx, getHeadY() + dy);

        checkBorders(head);
        if (!alive) {
            return;
        }

        checkBody(head);
        if (!alive) {
            return;
        }

        nextStep(head);
        checkEatMouse(head);
    }

    private void checkBorders(@NonNull final SnakeSection head) {
        val headX = head.getX();
        val headY = head.getY();

        val setting = Setting.getInstance(); // FIXME: DELETE DEPENDENCY

        alive = (headX >= 1 && headX < setting.getRoomWidth() + 2)
                && (headY >= 1 && headY < setting.getRoomHeight() + 2);
    }

    private void checkBody(@NonNull final SnakeSection head) {
        if (sections.contains(head)) {
            alive = false;
        }
    }

    private void nextStep(@NonNull final SnakeSection head) {
        sections.add(0, head);
    }

    private void checkEatMouse(@NonNull final SnakeSection head) {
        val mouse = Room.room.getMouse();

        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) {
            Room.room.eatMouse();
        } else {
            sections.remove(sections.size() - 1);
        }
    }
}
