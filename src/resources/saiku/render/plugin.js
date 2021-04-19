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

var Chart=Backbone.View.extend({initialize:function(a){this.workspace=a.workspace,this.id=_.uniqueId("chart_"),$(this.el).attr({id:this.id}),this.renderer=new SaikuChartRenderer(null,{htmlObject:$(this.el),zoom:!0,adjustSizeTo:".workspace_results"}),_.bindAll(this,"receive_data","show","render_view","exportChart");var b=this;this.workspace.bind("query:run",function(){return!!$(b.workspace.querytoolbar.el).find(".render_chart").hasClass("on")&&(b.renderer.data={},b.renderer.data.resultset=[],b.renderer.data.metadata=[],$(b.el).find(".canvas_wrapper").hide(),!1)}),this.workspace.bind("query:result",this.receive_data);var c="<div id='nav"+this.id+"' style='display:none' class='nav'><form id='svgChartPseudoForm' target='_blank' method='POST'><input type='hidden' name='type' class='type'/><input type='hidden' name='svg' class='svg'/><input type='hidden' name='name' class='name'/></form></div>";isIE&&(c="<div></div>"),this.nav=$(c),$(this.el).append(this.nav)},exportChart:function(a){var b=(new XMLSerializer).serializeToString($("svg")[0]),c='<svg xmlns="http://www.w3.org/2000/svg" ';b.substr(0,c.length)!=c&&(b=b.replace("<svg ",c)),b='<!DOCTYPE svg [<!ENTITY nbsp "&#160;">]>'+b;var d=$("#svgChartPseudoForm");if(d.find(".type").val(a),d.find(".svg").val(b),void 0!=this.workspace.query.name){var e=this.workspace.query.name.substring(this.workspace.query.name.lastIndexOf("/")+1).slice(0,-6);d.find(".name").val(e)}return d.attr("action",Settings.REST_URL+this.workspace.query.url()+escape("/../../export/saiku/chart")),d.submit(),!1},render_view:function(){$(this.workspace.el).find(".workspace_results").prepend($(this.el).hide())},show:function(a,b){var c=this;this.workspace.adjust(),this.renderer.cccOptions.width=$(this.workspace.el).find(".workspace_results").width()-40,this.renderer.cccOptions.height=$(this.workspace.el).find(".workspace_results").height()-40,$(this.el).show();var d=this.workspace.query.result.hasRun();d&&_.defer(function(){c.renderer.process_data_tree({data:c.workspace.query.result.lastresult()},!0,!0),c.renderer.switch_chart(c.renderer.type,{workspace:c.workspace})})},export_button:function(a){var b=this,c=$(a.target).hasClass("button")?$(a.target):$(a.target).parent(),b=this;$(document);$.contextMenu("destroy",".export_button"),$.contextMenu({selector:".export_button",trigger:"left",ignoreRightClick:!0,callback:function(a,c){b.workspace.chart.exportChart(a)},items:{png:{name:"PNG"},jpg:{name:"JPEG"},pdf:{name:"PDF"}}}),c.contextMenu()},receive_data:function(a){$(this.workspace.querytoolbar.el).find(".render_chart").hasClass("on")&&(this.workspace.adjust(),this.renderer.process_data_tree(a,!0,!0),this.renderer.switch_chart(this.renderer.type,{workspace:this.workspace}))}});

