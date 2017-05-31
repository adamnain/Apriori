/*
 * Copyright 2017 Michael Rapp
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
package de.mrapp.apriori.operators;

import de.mrapp.apriori.AssociationRule;
import de.mrapp.apriori.Metric;
import de.mrapp.apriori.Operator;
import de.mrapp.apriori.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;

/**
 * An operator, which allows to average the heuristic values, which are calculated by applying
 * multiple metrics to a rule, according to the arithmetic mean operation.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public class ArithmeticMean implements Operator {

    /**
     * A collection, which contains the metrics, which have been added to the arithmetic mean
     * operator.
     */
    private final Collection<Pair<Metric, Integer>> metrics;

    /**
     * Creates a new arithmetic mean operator.
     */
    public ArithmeticMean() {
        this.metrics = new LinkedList<>();
    }

    /**
     * Adds a new metric to the arithmetic operator.
     *
     * @param metric The metric, which should be added, as an instance of the type {@link Metric}.
     *               The metric may not be null
     * @return The arithmetic mean operator, this method has been called upon, as an instance of the
     * class {@link ArithmeticMean}. The operator may not be null
     */
    @NotNull
    public final ArithmeticMean add(@NotNull final Metric metric) {
        return add(metric, 1);
    }

    /**
     * Adds a new metric to the arithmetic mean operator.
     *
     * @param metric The metric, which should be added, as an instance of the type {@link Metric}.
     *               The metric may not be null
     * @param weight The weight of the metric, which should be added, as an {@link Integer} value.
     *               The weight must be at least 1
     * @return The arithmetic mean operator, this method has been called upon, as an instance of the
     * class {@link ArithmeticMean}. The operator may not be null
     */
    @NotNull
    public final ArithmeticMean add(@NotNull final Metric metric, final int weight) {
        // TODO: Throw exceptions
        metrics.add(Pair.create(metric, weight));
        return this;
    }

    @Override
    public final double average(@NotNull final AssociationRule<?> rule) {
        // TODO: Throw exception if no metric has been added
        double result = 0;
        int sumOfWeights = metrics.stream().mapToInt(x -> x.second).sum();

        for (Pair<Metric, Integer> pair : metrics) {
            Metric metric = pair.first;
            double heuristicValue = metric.evaluate(rule);
            int weight = pair.second;
            result += heuristicValue * ((double) weight / (double) sumOfWeights);
        }

        return result;
    }

}