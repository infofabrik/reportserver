/*
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/**
 * The global toolbar
 */
var Upgrade = Backbone.View.extend({

	events: {
	},


	initialize: function(a, b) {

		this.workspace = a.workspace;

		// Fire off workspace event
		this.workspace.trigger('workspace:toolbar:render', {
			workspace: this.workspace
		});

	},

	daydiff: function(first, second) {
		return Math.round((second-first)/(1000*60*60*24));
	},

	render: function() {
		
	},

	call: function(e) {
	}

});
