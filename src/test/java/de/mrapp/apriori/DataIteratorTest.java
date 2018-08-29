/*
 * Copyright 2017 - 2018 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.apriori;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the class {@link DataIterator}.
 *
 * @author Michael Rapp
 */
public class DataIteratorTest extends AbstractDataTest {

    /**
     * The data, which is contained by the first input file.
     */
    private static final String[][] DATA_1 = { { "bread", "butter", "sugar" }, { "coffee", "milk", "sugar" },
            { "bread", "coffee", "milk", "sugar" }, { "coffee", "milk" }
    };

    /**
     * The data, which is contained by the second input file.
     */
    private static final String[][] DATA_2 = { { "beer", "chips", "wine" }, { "beer", "chips" }, { "pizza", "wine" },
            { "chips", "pizza" }
    };

    /**
     * The data, which is contained by the third input file.
     */
    private static final String[][] DATA_3 = { { "0", "1", "2", "3" }, { "0", "1", "3", "4", "5" }, { "0", "1", "4" },
            { "0", "1", "4" }
    };

    /**
     * The data, which is contained by the fourth input file.
     */
    private static final String[][] DATA_4 = { { "0", "1", "2", "3" }, { "0", "1", "2", "3" },
            { "0", "1", "3", "4", "5" }, { "0", "1", "4" }, { "0", "1", "4" }
    };


    /**
     * Tests, if the data, which is contained by an input file, is iterated correctly.
     *
     * @param fileName   The file name of the input file as a {@link String}
     * @param actualData The data, which is contained by the input file, as a two-dimensional {@link
     *                   String} array. The array may not be null
     */
    private void testIterator(@NotNull final String fileName,
            @NotNull final String[][] actualData) {
        File inputFile = getInputFile(fileName);
        DataIterator iterator = new DataIterator(inputFile);
        int transactionCount = 0;

        while (iterator.hasNext()) {
            Transaction<NamedItem> transaction = iterator.next();
            String[] line = actualData[transactionCount];
            int index = 0;

            for (NamedItem item : transaction) {
                assertEquals(line[index], item.getName());
                index++;
            }

            transactionCount++;
        }

        assertEquals(actualData.length, transactionCount);
    }

    /**
     * Tests, if the data of the first input file is iterated correctly.
     */
    @Test
    public final void testIterator1() {
        testIterator(INPUT_FILE_1, DATA_1);
    }

    /**
     * Tests, if the data of the second input file is iterated correctly.
     */
    @Test
    public final void testIterator2() {
        testIterator(INPUT_FILE_2, DATA_2);
    }

    /**
     * Tests, if the data of the third input file is iterated correctly.
     */
    @Test
    public final void testIterator3() {
        testIterator(INPUT_FILE_3, DATA_3);
    }

    /**
     * Tests, if the data of the third input file is iterated correctly.
     */
    @Test
    public final void testIterator4() {
        testIterator(INPUT_FILE_4, DATA_4);
    }

}