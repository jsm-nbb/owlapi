/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.coode.owlapi.obo.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 18/04/2012 */
public class IDSpaceTagValueHandler extends AbstractTagValueHandler {
    private static final Pattern PATTERN = Pattern.compile("([^\\s]*)\\s+([^\\s]*)");
    private static final int ID_PREFIX_GROUP = 1;
    private static final int IRI_PREFIX_GROUP = 2;

    /** @param consumer */
    public IDSpaceTagValueHandler(OBOConsumer consumer) {
        super(OBOVocabulary.ID_SPACE.getName(), consumer);
    }

    @Override
    public void handle(String currentId, String value, String qualifierBlock,
            String comment) {
        Matcher matcher = PATTERN.matcher(value);
        if (matcher.matches()) {
            String idPrefix = matcher.group(ID_PREFIX_GROUP);
            String iriPrefix = matcher.group(IRI_PREFIX_GROUP);
            getConsumer().registerIdSpace(idPrefix, iriPrefix);
        }
    }
}
