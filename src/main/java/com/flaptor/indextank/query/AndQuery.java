/*
 * Copyright (c) 2011 LinkedIn, Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/*
Copyright 2008 Flaptor (flaptor.com) 

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License.
*/
package com.flaptor.indextank.query;

import java.io.Serializable;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;

/**
 * @author Flaptor Development Team
 */
public final class AndQuery extends BinaryQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    public AndQuery(final QueryNode lt, final QueryNode rt) {
        super(lt, rt);
    }

    @Override
    public org.apache.lucene.search.Query getLuceneQuery() {
        BooleanQuery bq = new BooleanQuery();
        if (leftQuery instanceof AndQuery) {
        	for (BooleanClause bc : ((BooleanQuery)leftQuery.getLuceneQuery()).getClauses()) {
        		bq.add(bc);
        	}
        } else {
            bq.add(leftQuery.getLuceneQuery(), BooleanClause.Occur.MUST);
        }
        if (rightQuery instanceof AndQuery) {
        	for (BooleanClause bc : ((BooleanQuery)rightQuery.getLuceneQuery()).getClauses()) {
        		bq.add(bc);
        	}        	
        } else {
            bq.add(rightQuery.getLuceneQuery(), BooleanClause.Occur.MUST);
        }
        return bq;
    }
    
    @Override
    public String toString() {
        return "( " + leftQuery.toString() + " ) AND ( " + rightQuery.toString() + " )" + boostString();
    }


    public QueryNode duplicate() {
        QueryNode qn = new AndQuery(this.leftQuery.duplicate(), this.rightQuery.duplicate());
        qn.setBoost(this.getBoost());
        qn.setNorm(this.getNorm());
        return qn;
    }
}
