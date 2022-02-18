package net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group.subsubcommands

import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.model.Verb
import com.google.inject.Inject

import groovy.json.JsonSlurper
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition
import net.datenwerke.rs.onedrive.service.onedrive.terminal.helper.OnedriveTerminalMethods
import net.datenwerke.rs.onedrive.service.onedrive.terminal.subcommands.group.OnedriveGroupSubSubCommandHook
import net.datenwerke.rs.terminal.service.terminal.TerminalSession
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult

class OnedriveGroupGetmygroupsSubSubCommand extends OnedriveTerminalMethods implements OnedriveGroupSubSubCommandHook{
   static final String BASE_COMMAND = "getmygroups"
   static final String REQUEST_URL_MEMBEROF = "https://graph.microsoft.com/v1.0/me/memberOf"
   static String REQUEST_URL_GROUPINFO = "https://graph.microsoft.com/v1.0/groups/[group-id]"
   private final JsonSlurper jsonSluper = new JsonSlurper()

   @Inject
   OnedriveGroupGetmygroupsSubSubCommand() {
   }

   @Override
   String getBaseCommand() {
      return BASE_COMMAND
   }

   @Override
   boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand())
   }

   @Override
   CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      def query = getQuery(parser)
      def optionalAccessToken = parser.getNonOptionArguments()[1]
      
      def oneDriveDatasink = getAndValidateOneDriveDatasink(query, session)
      def final oauthService = new ServiceBuilder(oneDriveDatasink.getAppKey())
                                    .apiSecret(oneDriveDatasink.getSecretKey())
                                    .build(oneDriveDatasink.getOAuthApi())
      def final refreshToken = oneDriveDatasink.getRefreshToken()
      def accessToken = (optionalAccessToken != null)? optionalAccessToken : getAccessToken(oauthService, refreshToken)
      
      def responseMemberOf = executeRequest(oauthService, new OAuthRequest(Verb.GET, REQUEST_URL_MEMBEROF), accessToken)
      if(responseMemberOf instanceof Exception) throw new TerminalException("Exception: $responseMemberOf.message", responseMemberOf)
      def mygroups = jsonSluper.parseText(responseMemberOf.body)
      
      def mygroupsInfo = mygroups.value.stream()
            .map {group -> executeRequest(oauthService, new OAuthRequest(Verb.GET, REQUEST_URL_GROUPINFO.replace("[group-id]", group.id)), accessToken)}
            .filter { response -> !(response instanceof Exception) }
            .map {respose -> jsonSluper.parseText(respose.body)}
            .toArray()

      
      def resultTable = getRSTableModel()
      mygroupsInfo.each{resultTable.addDataRow(new RSStringTableRow(it.displayName, it.id))}
      return new CommandResult(resultTable)
   }

   @Override
   void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

   private def getQuery(CommandParser parser) {
      if(parser.getNonOptionArguments().size() < 1)
         throw new IllegalArgumentException('Please enter a datasink id, name or query to identify the onedrive datasink')
      return parser.getNonOptionArguments()[0]
   }
   

   private def getRSTableModel() {
      def rsTableModel = new RSTableModel()
      def td = new TableDefinition(Arrays.asList("Group Name", "Group ID"),
            Arrays.asList(String.class, String.class))
      rsTableModel.setTableDefinition(td)
      return rsTableModel
   }
}
   
   
   
   
   
   
   
   
   
   