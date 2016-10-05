package com.vova_cons.sm_regex.state_machine;

/**
 * version: 1.0.0
 * Конечный автомат на основе символов (char)
 * Пути - имеют один триггер - char.
 * WARNING: mutable object! Don't use in concurrent operations!
 */
public interface StringFiniteAutomate {
    /**
     * Сбросить состояние конечного автомата в начальное.
     */
    void reset();

    /**
     * Пройти по пути (если такой есть в текущем состоянии)
     * @param trigger триггер пути
     * @throws Exception если нет найденного пути, вернется исключение и автомат сломается
     */
    void move(char trigger) throws Exception;

    /**
     * Получить максимально возможный путь к конечному состоянию.
     * @return путь к конечному состоянию или null
     */
    String getWayTrace();

    /**
     * Проверить, достиг ли конечный автомат до конечного состояния в процессе пути.
     * @return достигнуто ли хоть одно конечное состояние
     */
    boolean isFinished();

    /**
     * Check current state of finish state
     * @return current state are finished (boolean)
     */
    boolean isCurrentFinished();
}
