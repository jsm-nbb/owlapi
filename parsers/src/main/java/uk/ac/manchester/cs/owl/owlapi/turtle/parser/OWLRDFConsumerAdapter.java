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
 * Copyright 2011, University of Manchester
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
package uk.ac.manchester.cs.owl.owlapi.turtle.parser;

import org.coode.owlapi.rdfxml.parser.AnonymousNodeChecker;
import org.coode.owlapi.rdfxml.parser.OWLRDFConsumer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLRuntimeException;
import org.xml.sax.SAXException;

/** Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 24-Feb-2008<br>
 * <br> */
@SuppressWarnings("javadoc")
public class OWLRDFConsumerAdapter extends OWLRDFConsumer implements TripleHandler {
    public OWLRDFConsumerAdapter(OWLOntologyManager owlOntologyManager,
            OWLOntology ontology, AnonymousNodeChecker checker,
            OWLOntologyLoaderConfiguration configuration) {
        this(ontology, checker, configuration);
    }

    public OWLRDFConsumerAdapter(OWLOntology ontology, AnonymousNodeChecker checker,
            OWLOntologyLoaderConfiguration configuration) {
        super(ontology, checker, configuration);
    }

    @Override
    public void handlePrefixDirective(String prefixName, String prefix) {}

    @Override
    public void handleBaseDirective(String base) {
        // setXMLBase(base);
    }

    @Override
    public void handleComment(String comment) {}

    @Override
    public void handleTriple(IRI subject, IRI predicate, IRI object) {
        try {
            statementWithResourceValue(subject.toString(), predicate.toString(),
                    object.toString());
        } catch (SAXException e) {
            throw new OWLRuntimeException(e);
        }
    }

    @Override
    public void handleTriple(IRI subject, IRI predicate, String object) {
        try {
            statementWithLiteralValue(subject.toString(), predicate.toString(), object,
                    null, null);
        } catch (SAXException e) {
            throw new OWLRuntimeException(e);
        }
    }

    @Override
    public void handleTriple(IRI subject, IRI predicate, String object, String lang) {
        try {
            statementWithLiteralValue(subject.toString(), predicate.toString(), object,
                    lang, null);
        } catch (SAXException e) {
            throw new OWLRuntimeException(e);
        }
    }

    @Override
    public void handleTriple(IRI subject, IRI predicate, String object, IRI datatype) {
        try {
            statementWithLiteralValue(subject.toString(), predicate.toString(), object,
                    null, datatype.toString());
        } catch (SAXException e) {
            throw new OWLRuntimeException(e);
        }
    }

    @Override
    public void handleEnd() {
        try {
            endModel();
        } catch (SAXException e) {
            throw new OWLRuntimeException(e);
        }
    }
}
