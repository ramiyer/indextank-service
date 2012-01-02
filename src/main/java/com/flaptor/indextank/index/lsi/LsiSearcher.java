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

package com.flaptor.indextank.index.lsi;

import java.util.concurrent.BlockingDeque;

import org.apache.log4j.Logger;

import com.flaptor.indextank.index.DocId;
import com.flaptor.indextank.index.QueryMatcher;
import com.flaptor.indextank.index.TopMatches;
import com.flaptor.indextank.query.Query;
import com.flaptor.util.Execute;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

class LsiSearcher implements QueryMatcher {
    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(Execute.whoAmI());
    private LsiIndex index;

    LsiSearcher(LsiIndex index) {
        Preconditions.checkNotNull(index);
        this.index = index;
    }

    @Override
    public TopMatches findMatches(Query query, Predicate<DocId> idFilter, int limit, int scoringFunctionIndex) throws InterruptedException {
        BlockingDeque<QueryMatcher> queue = index.getQueryMatcherPool();
        QueryMatcher matcher = queue.takeFirst();
        try {
            return matcher.findMatches(query, idFilter, limit, scoringFunctionIndex);
        } finally {
            queue.putFirst(matcher);
        }
    }

    @Override
    public TopMatches findMatches(Query query, int limit, int scoringFunctionIndex) throws InterruptedException {
        BlockingDeque<QueryMatcher> queue = index.getQueryMatcherPool();
        QueryMatcher matcher = queue.takeFirst();
        try {
            return matcher.findMatches(query, limit, scoringFunctionIndex);
        } finally {
            queue.putFirst(matcher);
        }
    }

	@Override
	public boolean hasChanges(DocId docid) throws InterruptedException {
        BlockingDeque<QueryMatcher> queue = index.getQueryMatcherPool();
        QueryMatcher matcher = queue.takeFirst();
        try {
            return matcher.hasChanges(docid);
        } finally {
            queue.putFirst(matcher);
        }
	}

    @Override
    public int countMatches(Query query) throws InterruptedException {
        BlockingDeque<QueryMatcher> queue = index.getQueryMatcherPool();
        QueryMatcher matcher = queue.takeFirst();
        try {
            return matcher.countMatches(query);
        } finally {
            queue.putFirst(matcher);
        }
    }

    @Override
    public int countMatches(Query query, Predicate<DocId> idFilter) throws InterruptedException {
        BlockingDeque<QueryMatcher> queue = index.getQueryMatcherPool();
        QueryMatcher matcher = queue.takeFirst();
        try {
            return matcher.countMatches(query, idFilter);
        } finally {
            queue.putFirst(matcher);
        }
    }

	
	
}
