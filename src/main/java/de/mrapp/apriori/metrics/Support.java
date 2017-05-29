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
package de.mrapp.apriori.metrics;

import de.mrapp.apriori.AssociationRule;
import de.mrapp.apriori.Metric;
import org.jetbrains.annotations.NotNull;

/**
 * A metric, which measures the support of an association rule. By definition, support measures the
 * percentage of transactions for which the body and head of the rule is true.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
public class Support implements Metric {

    @Override
    public final double evaluate(@NotNull final AssociationRule<?> rule) {
        return rule.getSupport();
    }

}