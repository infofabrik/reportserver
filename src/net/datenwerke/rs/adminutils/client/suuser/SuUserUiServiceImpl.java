package net.datenwerke.rs.adminutils.client.suuser;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.adminutils.client.suuser.locale.SuMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.helper.simpleform.SFFCUserSelector;

@Singleton
public class SuUserUiServiceImpl implements SuUserUiService {

   private String userKey;
   private final SuUserDao suUserDao;
   private final UtilsUIService utilsService;

   @Inject
   public SuUserUiServiceImpl(final SuUserDao suUserDao, final UtilsUIService utilsService) {
      /* store objects */
      this.suUserDao = suUserDao;
      this.utilsService = utilsService;
   }

   @Override
   public void openSuWindow() {
      DwWindow window = new DwWindow();
      window.setModal(true);
      window.setHeaderIcon(BaseIcon.USER_SECRET);
      window.setSize(300, 180);
      window.setResizable(false);
      window.setHeading(SuMessages.INSTANCE.suPromptHeader());

      final SimpleForm form = SimpleForm.getInlineInstance();

      userKey = form.addField(UserDto.class, SuMessages.INSTANCE.userLabel(), new SFFCUserSelector() {
         @Override
         public UserDto getUser() {
            return (UserDto) form.getValue(userKey);
         }
      });

      form.loadFields();
      window.add(form, new MarginData(10));

      window.addCancelButton();
      window.addSubmitButton(() -> {
         final UserDto user = (UserDto) form.getValue(userKey);
         suUserDao.su(user.getId(), new RsAsyncCallback<Void>() {
            public void onSuccess(Void result) {
               utilsService.reloadPageWithoutAsking();
            };
         });
      });

      window.show();      
   }
   
}
