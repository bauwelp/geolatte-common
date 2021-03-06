/*
 * This file is part of the GeoLatte project.
 *
 *     GeoLatte is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     GeoLatte is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with GeoLatte.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010 - 2010 and Ownership of code is shared by:
 * Qmino bvba - Romeinsestraat 18 - 3001 Heverlee  (http://www.qmino.com)
 * Geovise bvba - Generaal Eisenhowerlei 9 - 2140 Antwerpen (http://www.geovise.com)
 */

package org.geolatte.common.cql;

import org.geolatte.common.transformer.Transformation;
import org.geolatte.common.transformer.TransformationException;

import java.text.ParseException;

/**
 * <p>
 * A {@link org.geolatte.common.transformer.Transformation} that applies a cql filter to its input object and returns a Boolean indicating whether the input passed the filter.
 * </p>
 * <p>
 * <i>Creation-Date</i>: 01-Jul-2010<br>
 * <i>Creation-Time</i>:  11:08:26<br>
 * </p>
 *
 * @author Bert Vanhooff
 * @author <a href="http://www.qmino.com">Qmino bvba</a>
 * @since SDK1.5
 */
public class CqlFilterTransformation<T> implements Transformation<T, Boolean> {

    private CqlFilter cqlFilter;

    /**
     * Initializes a <pre>CQLFilterTransformation</pre> using the given cql expression
     * @param cqlExpression The cql expression
     * @throws java.text.ParseException When the given cqlExpression could not be parsed.
     */
    public CqlFilterTransformation(String cqlExpression) throws ParseException {

        cqlFilter = Cql.toFilter(cqlExpression);
    }

    /**
     * Checks whether the given input object passes the cql filter.
     *
     * @param input The object to evaluate
     * @return True if the given object passes the cql filter, false otherwise.
     */
    public Boolean transform(T input) throws TransformationException {

        try {
            
            return cqlFilter.evaluate(input);
        }
        catch (ParseException e) {

            throw new TransformationException(e, input);
        }

    }
}
