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
package org.legacysaiku.service.datasource;

import java.util.List;
import java.util.Map;

import org.legacysaiku.datasources.datasource.SaikuDatasource;

public interface IDatasourceManager {
	
	public void load();
	
	public SaikuDatasource addDatasource(SaikuDatasource datasource);
	
	public SaikuDatasource setDatasource(SaikuDatasource datasource);
	
	public List<SaikuDatasource> addDatasources(List<SaikuDatasource> datasources);
	
	public boolean removeDatasource(String datasourceName);
	
	public Map<String, SaikuDatasource> getDatasources();

	public SaikuDatasource getDatasource(String datasourceName);

}
