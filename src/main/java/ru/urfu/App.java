package ru.urfu;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Тестирование скорости итерирования по HashMap и LinkedHashMap<br>
 * {@link RandomString генерация строки}<br>
 * {@link #iterateOver(Map)}
 */
public class App
{
    /**
     * Количество элементов в Map
     */
    private static final int MAP_SIZE = 1_000_000;

    public static void main( String[] args )
    {
        List<String> generatedStrings = generateStrings(MAP_SIZE);
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        for (int i = 0; i < generatedStrings.size(); i++)
        {
            String generated = generatedStrings.get(i);
            // Для чистоты эксперимента кладём в качестве ключей одни и те же строки,
            // в одном и том же порядке.
            hashMap.put(generated, i);
            linkedHashMap.put(generated, i);
        }

        iterateOver(linkedHashMap);
        iterateOver(hashMap);
    }

    /**
     * Итерироваться по всей Map, ничего не делая.
     * Замерить скорость итерации по Map
     */
    private static void iterateOver(Map<String, Integer> map)
    {
        Instant startTimer = Instant.now();
        // noinspection StatementWithEmptyBody
        for (Entry<String, Integer> ignored : map.entrySet())
        {
            // DO NOTHING
        }

        // При желании можно проверить скорость итерации по набору ключей
        /*
        for (String key : map.keySet())
        {
            // DO NOTHING
        }
        */
        Duration duration = Duration.between(startTimer, Instant.now());
        System.err.println("Iterate over " + map.getClass().getSimpleName()
                + " is finished. Total time = " +
                duration.getSeconds() + " seconds (" + duration.toMillis() + " millis)");
    }

    /**
     * Сгенерировать случайные строки
     * @param count количество строк
     * @return список строк
     */
    private static List<String> generateStrings(final int count)
    {
        RandomString randomString = new RandomString();
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++)
        {
            list.add(randomString.nextString());
        }
        return list;
    }
}

