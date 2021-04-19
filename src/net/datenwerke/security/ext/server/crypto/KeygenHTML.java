package net.datenwerke.security.ext.server.crypto;

public class KeygenHTML {

	
	
	public static String getCreateKeyHTMLCode(String postaction, String tokenvalue){
		String tmp = createKeyHtmlCode.replaceAll("\\$\\{POSTACTION\\}", postaction);  //$NON-NLS-1$
		return tmp.replaceAll("\\$\\{TOKENVALUE\\}", tokenvalue); //$NON-NLS-1$
	}
	
	public static String getInstallCertificateHtmlCoder(String base64Cert){
		return installCertificateHtmlCode.replaceAll("\\$\\{BASE64CERT\\}", base64Cert); //$NON-NLS-1$
	}
	
	
	private final static String installCertificateHtmlCode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"     \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" +  //$NON-NLS-1$
			"<html>\r\n" +  //$NON-NLS-1$
			"  <head>\r\n" +  //$NON-NLS-1$
			"    \r\n" +  //$NON-NLS-1$
			"  </head>\r\n" +  //$NON-NLS-1$
			"  <body>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<!--\r\n" +  //$NON-NLS-1$
			"	Courtesy of the ejbca project. Licensed\r\n" +  //$NON-NLS-1$
			"	under the term of the lgpl\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	see http://ejbca.org\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"-->  \r\n" +  //$NON-NLS-1$
			" \r\n" +  //$NON-NLS-1$
			"<object classid=\"clsid:884e2049-217d-11da-b2a4-000e7bbb2b09\" id=\"g_objClassFactory\"></object>\r\n" +  //$NON-NLS-1$
			"<!-- Updated w CertEnroll for Vista\r\n" +  //$NON-NLS-1$
			"Class ID: {884e2049-217d-11da-b2a4-000e7bbb2b09}\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"<!-- New updated enrollment activeX-control 2002-09-02 (Q323172)\r\n" +  //$NON-NLS-1$
			"New Xenroll.dll information:\r\n" +  //$NON-NLS-1$
			"Class ID: {127698e4-e730-4e5c-a2b1-21490a70c8a1}\r\n" +  //$NON-NLS-1$
			"sXEnrollVersion=\"5,131,3659,0\"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"New Scrdenrl.dll information:\r\n" +  //$NON-NLS-1$
			"Class ID: {c2bbea20-1f2b-492f-8a06-b1c5ffeace3b}\r\n" +  //$NON-NLS-1$
			"sScrdEnrlVersion=\"5,131,3642,0\"\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"<!-- Old Xenroll.dll information:\r\n" +  //$NON-NLS-1$
			"Class ID: {43F8F289-7A20-11D0-8F06-00C04FC295E1}\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"Old Scrdenrl.dll information:\r\n" +  //$NON-NLS-1$
			"Class ID: {80CB7887-20DE-11D2-8D5C-00C04FC29D45}\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	<script language=\"VBScript\" type=\"text/vbscript\">\r\n" +  //$NON-NLS-1$
			"    cert = \"${BASE64CERT}\"\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"		' This function can be moved to functions.vbs when the header is parsed as jsp\r\n" +  //$NON-NLS-1$
			"		Sub installcertvista\r\n" +  //$NON-NLS-1$
			"			Dim objEnroll\r\n" +  //$NON-NLS-1$
			"			Set objEnroll = g_objClassFactory.CreateObject(\"X509Enrollment.CX509Enrollment\")\r\n" +  //$NON-NLS-1$
			"			Call objEnroll.Initialize(1)	'EnrollmentContext UserContext\r\n" +  //$NON-NLS-1$
			"			err.clear\r\n" +  //$NON-NLS-1$
			"			On Error Resume Next\r\n" +  //$NON-NLS-1$
			"	        Call objEnroll.InstallResponse(0, cert, 6, \"\")	'AllowNone, , XCN_CRYPT_STRING_BASE64_ANY, pw\r\n" +  //$NON-NLS-1$
			"	        If err.number = -2146762487	Then	' 0x800b0109 Not trusted root\r\n" +  //$NON-NLS-1$
			"	        	r = Msgbox(\"Could not complete the request since, the CAs' certificates were not properly installed.\", , \"Certificate Management\")\r\n" +  //$NON-NLS-1$
			"			ElseIf err.number <> 0 Then\r\n" +  //$NON-NLS-1$
			"				r = Msgbox(\"The certificate could not be installed\"  & err.number , , \"Certificate Management\")\r\n" +  //$NON-NLS-1$
			"			Else\r\n" +  //$NON-NLS-1$
			"				r = Msgbox(\"A new certificate has been installed\", , \"Certificate Management\")\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"		End Sub\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"		Sub installcert\r\n" +  //$NON-NLS-1$
			"			Err.Clear\r\n" +  //$NON-NLS-1$
			"			On Error Resume Next\r\n" +  //$NON-NLS-1$
			"			g_objClassFactory.acceptPKCS7(cert)\r\n" +  //$NON-NLS-1$
			"			If Err.Number <> 0 Then\r\n" +  //$NON-NLS-1$
			"				r = Msgbox(\"The certificate could not be installed in this web browser\", , \"Certificate Management\")\r\n" +  //$NON-NLS-1$
			"			Else\r\n" +  //$NON-NLS-1$
			"				r = Msgbox (\"A new certificate has been installed\", , \"Certificate Management\")\r\n" +  //$NON-NLS-1$
			"			End if\r\n" +  //$NON-NLS-1$
			"		End Sub\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"		If InStr(navigator.userAgent, \"Windows NT 6\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"			installcertvista\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"			installcert\r\n" +  //$NON-NLS-1$
			"		End If\r\n" +  //$NON-NLS-1$
			"	</script>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	<h1 class=\"title\">Internet Explorer Certificate enrollment.</h1>\r\n" +  //$NON-NLS-1$
			"    <p>If the installation was completed without any errors, your certificate has\r\n" +  //$NON-NLS-1$
			"    been installed in your web browser and you may now start using your certificate.<br />\r\n" +  //$NON-NLS-1$
			"	You can look at your certificate with &quot;<tt>Tools-&gt;Internet\r\n" +  //$NON-NLS-1$
			"	Options-&gt;Content-&gt;Certificates</tt>&quot;.</p>\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"<!-- Footer -->\r\n" +  //$NON-NLS-1$
			"     \r\n" +  //$NON-NLS-1$
			"  </body>\r\n" +  //$NON-NLS-1$
			"</html>\r\n" +  //$NON-NLS-1$
			"<!-- Footer -->\r\n" +  //$NON-NLS-1$
			""; //$NON-NLS-1$
	
	
	private final static String createKeyHtmlCode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<html>\r\n" +  //$NON-NLS-1$
			"  <head>\r\n" +  //$NON-NLS-1$
			"  </head>\r\n" +  //$NON-NLS-1$
			"  <body>\r\n" +  //$NON-NLS-1$
			"    \r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	<!--\r\n" +  //$NON-NLS-1$
			"	 	Courtesy of the ejbca project. \r\n" +  //$NON-NLS-1$
			"	 	http://www.ejbca.org/\r\n" +  //$NON-NLS-1$
			"	 \r\n" +  //$NON-NLS-1$
			"	 	Licensed under the terms of the LGPL. See http://www.ejbca.org/license.html\r\n" +  //$NON-NLS-1$
			"	 -->\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"<!-- netscape -->\r\n" +  //$NON-NLS-1$
			"<form style=\"visibility: hidden\" action=\"${POSTACTION}\" id=\"keygenform\" enctype=\"x-www-form-encoded\" method=\"post\">\r\n" +  //$NON-NLS-1$
			"  <fieldset>\r\n" +  //$NON-NLS-1$
			"	<label for=\"keygen\">Key length:</label>\r\n" +  //$NON-NLS-1$
			"	<keygen name=\"keygen\" id=\"keygen\" challenge=\"${TOKENVALUE}\" />\r\n" +  //$NON-NLS-1$
			"	<input name=\"token\" type=\"hidden\" value=\"${TOKENVALUE}\" />\r\n" +  //$NON-NLS-1$
			"	<input type=\"submit\" id=\"ok\" value=\"OK\" />\r\n" +  //$NON-NLS-1$
			"  </fieldset>\r\n" +  //$NON-NLS-1$
			"</form>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<!-- internet exploder -->    \r\n" +  //$NON-NLS-1$
			"<script type=\"text/javascript\">\r\n" +  //$NON-NLS-1$
			"<!--\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	function autosubmit (formName) {\r\n" +  //$NON-NLS-1$
			"		var kgf = document.getElementById(formName);\r\n" +  //$NON-NLS-1$
			"		kgf.submit();\r\n" +  //$NON-NLS-1$
			"	}\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	if (navigator.appName.indexOf(\"Explorer\") != -1) {\r\n" +  //$NON-NLS-1$
			"	    if ( navigator.userAgent.indexOf(\"Windows NT 6\") != -1 ) {\r\n" +  //$NON-NLS-1$
			"				document.writeln(\"<object classid=\\\"clsid:884e2049-217d-11da-b2a4-000e7bbb2b09\\\" id=\\\"g_objClassFactory\\\" height=\\\"0\\\" width=\\\"0\\\" ></object>\");\r\n" +  //$NON-NLS-1$
			"			} else {\r\n" +  //$NON-NLS-1$
			"				document.writeln(\"<object classid=\\\"clsid:127698e4-e730-4e5c-a2b1-21490a70c8a1\\\" id=\\\"newencoder\\\" codebase=\\\"/CertControl/xenroll.cab#Version=5,131,3659,0\\\" height=\\\"0\\\" width=\\\"0\\\" ></object>\");\r\n" +  //$NON-NLS-1$
			"				document.writeln(\"<object classid=\\\"clsid:43F8F289-7A20-11D0-8F06-00C04FC295E1\\\" id=\\\"oldencoder\\\" height=\\\"0\\\" width=\\\"0\\\" ></object>\");\r\n" +  //$NON-NLS-1$
			"			}\r\n" +  //$NON-NLS-1$
			"			\r\n" +  //$NON-NLS-1$
			"		} else {\r\n" +  //$NON-NLS-1$
			"			window.onload = autosubmit(\"keygenform\");\r\n" +  //$NON-NLS-1$
			"		}\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"</script>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<script type=\"text/vbscript\">\r\n" +  //$NON-NLS-1$
			"<!--\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	Dim g_objEnroll, g_objPrivateKey, g_objRequest, g_objCSPInformations, g_certEnrollLoadError\r\n" +  //$NON-NLS-1$
			"	' Used by apply_exp.jspf\r\n" +  //$NON-NLS-1$
			"	Function InitVistaCSP()\r\n" +  //$NON-NLS-1$
			"		On Error resume next\r\n" +  //$NON-NLS-1$
			"		Set g_objEnroll				= g_objClassFactory.CreateObject(\"X509Enrollment.CX509Enrollment\")\r\n" +  //$NON-NLS-1$
			"		Set g_objPrivateKey			= g_objClassFactory.CreateObject(\"X509Enrollment.CX509PrivateKey\")\r\n" +  //$NON-NLS-1$
			"		Set g_objRequest			= g_objClassFactory.CreateObject(\"X509Enrollment.CX509CertificateRequestPkcs10\")\r\n" +  //$NON-NLS-1$
			"		Set g_objCSPInformations	= g_objClassFactory.CreateObject(\"X509Enrollment.CCspInformations\")                 \r\n" +  //$NON-NLS-1$
			"		If 0<>Err.Number Then\r\n" +  //$NON-NLS-1$
			"			g_certEnrollLoadError = Err.Number\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"			g_certEnrollLoadError = 0\r\n" +  //$NON-NLS-1$
			"			g_objCSPInformations.AddAvailableCsps  \r\n" +  //$NON-NLS-1$
			"		End If\r\n" +  //$NON-NLS-1$
			"		On Error Goto 0\r\n" +  //$NON-NLS-1$
			"	End Function\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	' Used by apply_exp.jspf\r\n" +  //$NON-NLS-1$
			"	Function GetCertEnrollCSPList()\r\n" +  //$NON-NLS-1$
			"	    On Error Resume Next\r\n" +  //$NON-NLS-1$
			"	    Dim nDefaultCSP, nCSPIndex, CspInformations, CspInformation, oOption\r\n" +  //$NON-NLS-1$
			"		Set CspInformations = g_objCSPInformations\r\n" +  //$NON-NLS-1$
			"	    nDefaultCSP = -1\r\n" +  //$NON-NLS-1$
			"	    ' Add error message if no CSPs are found\r\n" +  //$NON-NLS-1$
			"	    If CspInformations.Count = 0 Then\r\n" +  //$NON-NLS-1$
			"		    Set oOption=document.createElement(\"Option\")\r\n" +  //$NON-NLS-1$
			"		    oOption.text=\"N/A\"\r\n" +  //$NON-NLS-1$
			"			document.CertReqForm.CspProvider.add(oOption)\r\n" +  //$NON-NLS-1$
			"        Else \r\n" +  //$NON-NLS-1$
			"        	'Loop through all CspInformation objects\r\n" +  //$NON-NLS-1$
			"        	For nCSPIndex = 0 To CspInformations.Count-1\r\n" +  //$NON-NLS-1$
			"				Set CspInformation = CspInformations.ItemByIndex(nCSPIndex)\r\n" +  //$NON-NLS-1$
			"				If True = CspInformation.LegacyCsp Then	'Make sure that it's a Next Generation (CNG) provider\r\n" +  //$NON-NLS-1$
			"					Set oOption = document.createElement(\"Option\")\r\n" +  //$NON-NLS-1$
			"					oOption.text = CspInformation.Name\r\n" +  //$NON-NLS-1$
			"					oOption.Value = CspInformation.Type\r\n" +  //$NON-NLS-1$
			"					document.CertReqForm.CspProvider.add(oOption)\r\n" +  //$NON-NLS-1$
			"					If InStr(CspInformation.Name, \"Microsoft Enhanced Cryptographic Provider\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"						oOption.selected = True\r\n" +  //$NON-NLS-1$
			"						nDefaultCSP = nCSPIndex\r\n" +  //$NON-NLS-1$
			"					End If\r\n" +  //$NON-NLS-1$
			"					If InStr(CspInformation.Name, \"Microsoft Base Cryptographic Provider\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"						If nDefaultCSP = -1 Then nDefaultCSP = nCSPIndex\r\n" +  //$NON-NLS-1$
			"					End If\r\n" +  //$NON-NLS-1$
			"       			End If\r\n" +  //$NON-NLS-1$
			"	        Next\r\n" +  //$NON-NLS-1$
			"	    End If ' if 0 == CspInformations.Count\r\n" +  //$NON-NLS-1$
			"	    If nDefaultCSP <> -1 Then\r\n" +  //$NON-NLS-1$
			"		    Document.CertReqForm.CspProvider.selectedIndex = nDefaultCSP\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"		    Document.CertReqForm.CspProvider.selectedIndex = 0	'Select first or N/A\r\n" +  //$NON-NLS-1$
			"	    End If\r\n" +  //$NON-NLS-1$
			"    End Function	'GetCertEnrollCSPList\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	If InStr(Navigator.UserAgent, \"Windows NT 6\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"		InitVistaCSP()\r\n" +  //$NON-NLS-1$
			"	End if\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	' Code for IE under XP or earlier. Placed here since script src=... does not seem to work.\r\n" +  //$NON-NLS-1$
			"    Dim useold\r\n" +  //$NON-NLS-1$
			"    useold=false\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"    Function GetProviderList()\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	   Dim CspList, cspIndex, ProviderName\r\n" +  //$NON-NLS-1$
			"	   On Error Resume Next\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	   count = 0\r\n" +  //$NON-NLS-1$
			"	   base = -1\r\n" +  //$NON-NLS-1$
			"	   enhanced = 0\r\n" +  //$NON-NLS-1$
			"	   CspList = \"\"\r\n" +  //$NON-NLS-1$
			"	   ProviderName = \"\"\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	   For ProvType = 0 to 13\r\n" +  //$NON-NLS-1$
			"	      cspIndex = 0\r\n" +  //$NON-NLS-1$
			"	      newencoder.ProviderType = ProvType\r\n" +  //$NON-NLS-1$
			"	      ProviderName = newencoder.enumProviders(cspIndex,0)\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	      while ProviderName <> \"\"\r\n" +  //$NON-NLS-1$
			"	         Set oOption = document.createElement(\"option\")\r\n" +  //$NON-NLS-1$
			"	         oOption.text = ProviderName\r\n" +  //$NON-NLS-1$
			"	         oOption.value = ProvType\r\n" +  //$NON-NLS-1$
			"	         Document.CertReqForm.CspProvider.add(oOption)\r\n" +  //$NON-NLS-1$
			"	         if ProviderName = \"Microsoft Base Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"	            base = count\r\n" +  //$NON-NLS-1$
			"	         end if\r\n" +  //$NON-NLS-1$
			"	         if ProviderName = \"Microsoft Enhanced Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"	            enhanced = count\r\n" +  //$NON-NLS-1$
			"	         end if\r\n" +  //$NON-NLS-1$
			"	         cspIndex = cspIndex +1\r\n" +  //$NON-NLS-1$
			"	         ProviderName = \"\"\r\n" +  //$NON-NLS-1$
			"	         ProviderName = newencoder.enumProviders(cspIndex,0)\r\n" +  //$NON-NLS-1$
			"	         count = count + 1\r\n" +  //$NON-NLS-1$
			"	      wend\r\n" +  //$NON-NLS-1$
			"	   Next\r\n" +  //$NON-NLS-1$
			"	   If base = -1 Then\r\n" +  //$NON-NLS-1$
			"	     useold=true\r\n" +  //$NON-NLS-1$
			"	     Document.CertReqForm.classid.value=\"clsid:43F8F289-7A20-11D0-8F06-00C04FC295E1\"\r\n" +  //$NON-NLS-1$
			"	     count = 0\r\n" +  //$NON-NLS-1$
			"	     enhanced = 0\r\n" +  //$NON-NLS-1$
			"	     basename = \"\"\r\n" +  //$NON-NLS-1$
			"	     enhancedname = \"\"\r\n" +  //$NON-NLS-1$
			"	     CspList = \"\"\r\n" +  //$NON-NLS-1$
			"	     ProviderName = \"\"\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	     For ProvType = 0 to 13\r\n" +  //$NON-NLS-1$
			"	         cspIndex = 0\r\n" +  //$NON-NLS-1$
			"	         oldencoder.ProviderType = ProvType\r\n" +  //$NON-NLS-1$
			"	         ProviderName = oldencoder.enumProviders(cspIndex,0)\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	        while ProviderName <> \"\"\r\n" +  //$NON-NLS-1$
			"	           Set oOption = document.createElement(\"option\")\r\n" +  //$NON-NLS-1$
			"	           oOption.text = ProviderName\r\n" +  //$NON-NLS-1$
			"	           oOption.value = ProvType\r\n" +  //$NON-NLS-1$
			"	           Document.CertReqForm.CspProvider.add(oOption)\r\n" +  //$NON-NLS-1$
			"	           if ProviderName = \"Microsoft Base Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"	            base = count\r\n" +  //$NON-NLS-1$
			"	           end if\r\n" +  //$NON-NLS-1$
			"	           if ProviderName = \"Microsoft Enhanced Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"	            enhanced = count\r\n" +  //$NON-NLS-1$
			"	           end if\r\n" +  //$NON-NLS-1$
			"	           cspIndex = cspIndex +1\r\n" +  //$NON-NLS-1$
			"	           ProviderName = \"\"\r\n" +  //$NON-NLS-1$
			"	           ProviderName = oldencoder.enumProviders(cspIndex,0)\r\n" +  //$NON-NLS-1$
			"	           count = count + 1\r\n" +  //$NON-NLS-1$
			"	        wend\r\n" +  //$NON-NLS-1$
			"	     Next\r\n" +  //$NON-NLS-1$
			"	   End If \r\n" +  //$NON-NLS-1$
			"	   Document.CertReqForm.CspProvider.selectedIndex = base\r\n" +  //$NON-NLS-1$
			"	   if enhanced then\r\n" +  //$NON-NLS-1$
			"	      Document.CertReqForm.CspProvider.selectedIndex = enhanced\r\n" +  //$NON-NLS-1$
			"	   end if\r\n" +  //$NON-NLS-1$
			"   End Function\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"   Function NewCSR(keyflags)\r\n" +  //$NON-NLS-1$
			"      NewCSR = \"\"\r\n" +  //$NON-NLS-1$
			"      szName = \"CN=6AEK347fw8vWE424\"\r\n" +  //$NON-NLS-1$
			"       newencoder.reset  \r\n" +  //$NON-NLS-1$
			"       newencoder.HashAlgorithm = \"MD5\"\r\n" +  //$NON-NLS-1$
			"       err.clear\r\n" +  //$NON-NLS-1$
			"       On Error Resume Next\r\n" +  //$NON-NLS-1$
			"       set options = document.all.CspProvider.options\r\n" +  //$NON-NLS-1$
			"       index = options.selectedIndex\r\n" +  //$NON-NLS-1$
			"       newencoder.providerName = options(index).text\r\n" +  //$NON-NLS-1$
			"       tmpProviderType = options(index).value\r\n" +  //$NON-NLS-1$
			"       newencoder.providerType = tmpProviderType\r\n" +  //$NON-NLS-1$
			"       if Document.CertReqForm.enchancedeid.checked then      \r\n" +  //$NON-NLS-1$
			"         newencoder.ContainerName = \"\\Prime EID IP1 (basic PIN)\\E\"\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       newencoder.KeySpec = 2\r\n" +  //$NON-NLS-1$
			"       if tmpProviderType < 2 Then\r\n" +  //$NON-NLS-1$
			"          newencoder.KeySpec = 1\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       \r\n" +  //$NON-NLS-1$
			"       keysize = document.CertReqForm.keysize.value\r\n" +  //$NON-NLS-1$
			"       keymask = keysize * 65536\r\n" +  //$NON-NLS-1$
			"       \r\n" +  //$NON-NLS-1$
			"       newencoder.GenKeyFlags = keymask OR keyflags\r\n" +  //$NON-NLS-1$
			" \r\n" +  //$NON-NLS-1$
			"       NewCSR = newencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"       if len(NewCSR)<>0 then Exit Function\r\n" +  //$NON-NLS-1$
			"       if newencoder.providerName = \"Microsoft Enhanced Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"          if MsgBox(\"1024-bit key generation failed. Would you like to try 512 instead?\", vbOkCancel)=vbOk Then\r\n" +  //$NON-NLS-1$
			"             newencoder.providerName = \"Microsoft Base Cryptographic Provider v1.0\"\r\n" +  //$NON-NLS-1$
			"          else\r\n" +  //$NON-NLS-1$
			"             Exit Function\r\n" +  //$NON-NLS-1$
			"          end if\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       newencoder.GenKeyFlags = keyflags\r\n" +  //$NON-NLS-1$
			"       NewCSR = newencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"       if len(NewCSR)<>0 then Exit Function\r\n" +  //$NON-NLS-1$
			"       newencoder.GenKeyFlags = 0\r\n" +  //$NON-NLS-1$
			"       NewCSR = newencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"    End Function\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"   Function OldCSR(keyflags)\r\n" +  //$NON-NLS-1$
			"      OldCSR = \"\"\r\n" +  //$NON-NLS-1$
			"      szName = \"CN=6AEK347fw8vWE424\"\r\n" +  //$NON-NLS-1$
			"       oldencoder.reset\r\n" +  //$NON-NLS-1$
			"       oldencoder.HashAlgorithm = \"MD5\"\r\n" +  //$NON-NLS-1$
			"       err.clear\r\n" +  //$NON-NLS-1$
			"       On Error Resume Next\r\n" +  //$NON-NLS-1$
			"       set options = document.all.CspProvider.options\r\n" +  //$NON-NLS-1$
			"       index = options.selectedIndex\r\n" +  //$NON-NLS-1$
			"       oldencoder.providerName = options(index).text\r\n" +  //$NON-NLS-1$
			"       tmpProviderType = options(index).value\r\n" +  //$NON-NLS-1$
			"       oldencoder.providerType = tmpProviderType\r\n" +  //$NON-NLS-1$
			"       if Document.CertReqForm.enchancedeid.checked then         \r\n" +  //$NON-NLS-1$
			"         oldencoder.ContainerName = \"\\Prime EID IP1 (basic PIN)\\E\"\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       oldencoder.KeySpec = 2\r\n" +  //$NON-NLS-1$
			"       if tmpProviderType < 2 Then\r\n" +  //$NON-NLS-1$
			"          oldencoder.KeySpec = 1\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       \r\n" +  //$NON-NLS-1$
			"       keysize = document.CertReqForm.keysize.value\r\n" +  //$NON-NLS-1$
			"       keymask = keysize * 65536\r\n" +  //$NON-NLS-1$
			"       \r\n" +  //$NON-NLS-1$
			"       oldencoder.GenKeyFlags = keymask OR keyflags\r\n" +  //$NON-NLS-1$
			"       OldCSR = oldencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"       if len(OldCSR)<>0 then Exit Function\r\n" +  //$NON-NLS-1$
			"       if oldencoder.providerName = \"Microsoft Enhanced Cryptographic Provider v1.0\" Then\r\n" +  //$NON-NLS-1$
			"          if MsgBox(\"1024-bit key generation failed. Would you like to try 512 instead?\", vbOkCancel)=vbOk Then\r\n" +  //$NON-NLS-1$
			"             oldencoder.providerName = \"Microsoft Base Cryptographic Provider v1.0\"\r\n" +  //$NON-NLS-1$
			"          else\r\n" +  //$NON-NLS-1$
			"             Exit Function\r\n" +  //$NON-NLS-1$
			"          end if\r\n" +  //$NON-NLS-1$
			"       end if\r\n" +  //$NON-NLS-1$
			"       oldencoder.GenKeyFlags = keyflags\r\n" +  //$NON-NLS-1$
			"       OldCSR = oldencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"       if len(OldCSR)<>0 then Exit Function\r\n" +  //$NON-NLS-1$
			"       oldencoder.GenKeyFlags = 0\r\n" +  //$NON-NLS-1$
			"       OldCSR = oldencoder.createPKCS10(szName, \"\")\r\n" +  //$NON-NLS-1$
			"    End Function\r\n" +  //$NON-NLS-1$
			"    \r\n" +  //$NON-NLS-1$
			"   	' Used b apply_exp.jspf\r\n" +  //$NON-NLS-1$
			"	Function GetCSR(exportflag)\r\n" +  //$NON-NLS-1$
			"		GetCSR = \"\"\r\n" +  //$NON-NLS-1$
			"		' Get provider name and type\r\n" +  //$NON-NLS-1$
			"		Dim ProviderName, ProviderType\r\n" +  //$NON-NLS-1$
			"		ProviderName = document.all.CspProvider.options(document.all.CspProvider.options.selectedIndex).text\r\n" +  //$NON-NLS-1$
			"		ProviderType = document.all.CspProvider.options(document.all.CspProvider.options.selectedIndex).value\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.ProviderName = ProviderName\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.ProviderType = ProviderType\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.Length = document.CertReqForm.keysize.value\r\n" +  //$NON-NLS-1$
			"		If ProviderType < 2 Then\r\n" +  //$NON-NLS-1$
			"			g_objPrivateKey.KeySpec = 1	'AT_KEYEXCHANGE\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"			g_objPrivateKey.KeySpec = 2	'AT_SIGNATURE\r\n" +  //$NON-NLS-1$
			"		End If\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.MachineContext = false\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.KeyProtection = 1	' (XCN_NCRYPT_UI_PROTECT_KEY_FLAG = 1)\r\n" +  //$NON-NLS-1$
			"		g_objPrivateKey.ExportPolicy = exportflag	' (XCN_NCRYPT_ALLOW_EXPORT_FLAG = 1)\r\n" +  //$NON-NLS-1$
			"	    If Document.CertReqForm.enchancedeid.checked then\r\n" +  //$NON-NLS-1$
			"	    	If Document.CertReqForm.containername = \"\" Then\r\n" +  //$NON-NLS-1$
			"				g_objPrivateKey.ContainerName = \"\\Prime EID IP1 (basic PIN)\\E\"\r\n" +  //$NON-NLS-1$
			"			Else\r\n" +  //$NON-NLS-1$
			"				g_objPrivateKey.ContainerName = Document.CertReqForm.containername.value\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"			g_objPrivateKey.Existing = True\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"			g_objPrivateKey.Existing = False\r\n" +  //$NON-NLS-1$
			"		End if\r\n" +  //$NON-NLS-1$
			"		' Initialize\r\n" +  //$NON-NLS-1$
			"		Call g_objRequest.InitializeFromPrivateKey(1, g_objPrivateKey, \"\")	'X509CertificateEnrollmentContext.ContextUser\r\n" +  //$NON-NLS-1$
			"		Dim X500DistinguishedName\r\n" +  //$NON-NLS-1$
			"		Set X500DistinguishedName = g_objClassFactory.CreateObject(\"X509Enrollment.CX500DistinguishedName\")\r\n" +  //$NON-NLS-1$
			"		Call X500DistinguishedName.Encode(\"CN=6AEK347fw8vWE424\", 0)	'XCN_CERT_NAME_STR_NONE\r\n" +  //$NON-NLS-1$
			"		g_objRequest.Subject = X500DistinguishedName\r\n" +  //$NON-NLS-1$
			"		' Set hash algo\r\n" +  //$NON-NLS-1$
			"		Dim CspInformation, CspAlgorithms, CspAlgorithm, nBestIndex, nAlgIndex\r\n" +  //$NON-NLS-1$
			"		Set CspInformation = g_objCSPInformations.ItemByName(ProviderName)\r\n" +  //$NON-NLS-1$
			"		Set CspAlgorithms = CspInformation.CspAlgorithms\r\n" +  //$NON-NLS-1$
			"		nBestIndex = 0\r\n" +  //$NON-NLS-1$
			"		For nAlgIndex=0 To CspAlgorithms.Count-1\r\n" +  //$NON-NLS-1$
			"			If CspAlgorithms.ItemByIndex(nAlgIndex).Name = \"sha1\" Then\r\n" +  //$NON-NLS-1$
			"				nBestIndex = nAlgIndex\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"			If CspAlgorithms.ItemByIndex(nAlgIndex).Name = \"md5\" AND CspAlgorithms.ItemByIndex(nBestIndex).Name <> \"sha1\" Then\r\n" +  //$NON-NLS-1$
			"				nBestIndex = nAlgIndex\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"		Next\r\n" +  //$NON-NLS-1$
			"		Set CspAlgorithm = CspAlgorithms.ItemByIndex(nBestIndex)\r\n" +  //$NON-NLS-1$
			"		If CspAlgorithm.Type = 2 Then	'XCN_CRYPT_HASH_INTERFACE\r\n" +  //$NON-NLS-1$
			"			g_objRequest.HashAlgorithm = CspAlgorithm.GetAlgorithmOid(0, 0)	', AlgorithmFlagsNone\r\n" +  //$NON-NLS-1$
			"		End if\r\n" +  //$NON-NLS-1$
			"		' Try to create request\r\n" +  //$NON-NLS-1$
			"		g_objEnroll.InitializeFromRequest(g_objRequest)\r\n" +  //$NON-NLS-1$
			"		GetCSR = g_objEnroll.CreateRequest(3)	'CRYPT_STRING_BASE64REQUESTHEADER\r\n" +  //$NON-NLS-1$
			"		if len(GetCSR)<>0 then Exit Function\r\n" +  //$NON-NLS-1$
			"	End Function	'GetCSR\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	Function ControlExists(objectID)\r\n" +  //$NON-NLS-1$
			"		on error resume next\r\n" +  //$NON-NLS-1$
			"		ControlExists = IsObject(CreateObject(objectID))\r\n" +  //$NON-NLS-1$
			"	End Function\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	' Used by both post and pre Vista code	\r\n" +  //$NON-NLS-1$
			"	Sub GenReq_OnClick\r\n" +  //$NON-NLS-1$
			"		Dim TheForm, result\r\n" +  //$NON-NLS-1$
			"		Set TheForm = Document.CertReqForm\r\n" +  //$NON-NLS-1$
			"		err.clear\r\n" +  //$NON-NLS-1$
			"		If InStr(Navigator.UserAgent, \"Windows NT 6\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"			If g_certEnrollLoadError <> 0 Then\r\n" +  //$NON-NLS-1$
			"				Call MsgBox(\"Could not load CertEnroll.\", 0, \"Alert\")\r\n" +  //$NON-NLS-1$
			"				Exit Sub\r\n" +  //$NON-NLS-1$
			"			End if\r\n" +  //$NON-NLS-1$
			"			If Document.CertReqForm.exportable.checked then\r\n" +  //$NON-NLS-1$
			"				result = GetCSR(1)\r\n" +  //$NON-NLS-1$
			"			Else\r\n" +  //$NON-NLS-1$
			"				result = GetCSR(0)\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"		Else\r\n" +  //$NON-NLS-1$
			"			If useold Then\r\n" +  //$NON-NLS-1$
			"				If Document.CertReqForm.exportable.checked then\r\n" +  //$NON-NLS-1$
			"					result = OldCSR(2 + 1)\r\n" +  //$NON-NLS-1$
			"				Else\r\n" +  //$NON-NLS-1$
			"					result = OldCSR(2)\r\n" +  //$NON-NLS-1$
			"				End If\r\n" +  //$NON-NLS-1$
			"			Else\r\n" +  //$NON-NLS-1$
			"				If Document.CertReqForm.exportable.checked then\r\n" +  //$NON-NLS-1$
			"					result = NewCSR(2 + 1)\r\n" +  //$NON-NLS-1$
			"				Else\r\n" +  //$NON-NLS-1$
			"					result = NewCSR(2)\r\n" +  //$NON-NLS-1$
			"				End If\r\n" +  //$NON-NLS-1$
			"			End If\r\n" +  //$NON-NLS-1$
			"		End If\r\n" +  //$NON-NLS-1$
			"		if len(result)=0 Then\r\n" +  //$NON-NLS-1$
			"			result = MsgBox(\"Unable to generate PKCS#10 certificate request.\", 0, \"Alert\")\r\n" +  //$NON-NLS-1$
			"			Exit Sub\r\n" +  //$NON-NLS-1$
			"		End If\r\n" +  //$NON-NLS-1$
			"		TheForm.pkcs10.Value = result\r\n" +  //$NON-NLS-1$
			"		TheForm.Submit\r\n" +  //$NON-NLS-1$
			"		Exit Sub\r\n" +  //$NON-NLS-1$
			"	End Sub	'GenReq_OnClick\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"-->      \r\n" +  //$NON-NLS-1$
			"</script>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<div style=\"visibility:hidden\" id=\"explorercode\">\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<form name=\"CertReqForm\" action=\"${POSTACTION}\" enctype=\"x-www-form-encoded\" method=\"POST\">\r\n" +  //$NON-NLS-1$
			"	<input name=\"token\" type=\"hidden\" value=\"${TOKENVALUE}\" />\r\n" +  //$NON-NLS-1$
			"	<input name=\"classid\" type=\"hidden\" value=\"\" />\r\n" +  //$NON-NLS-1$
			"    <input name=\"pkcs10\" type=\"hidden\" value=\"\" />\r\n" +  //$NON-NLS-1$
			"    <input name=\"containername\" type=\"hidden\" value=\"\" />\r\n" +  //$NON-NLS-1$
			"	<input type=\"hidden\" name=\"keysize\" id=\"keysize\" value=\"2048\" />\r\n" +  //$NON-NLS-1$
			"    <input type=\"hidden\" name=\"enchancedeid\" value=\"true\" /> \r\n" +  //$NON-NLS-1$
			"    <input type=\"hidden\" name=\"exportable\" value=\"true\" /> \r\n" +  //$NON-NLS-1$
			"	<select style=\"visibility: hidden\" disabled=\"true\" name=\"CspProvider\" id=\"CspProvider\" accesskey=\"p\"></select>\r\n" +  //$NON-NLS-1$
			"	<input type=\"button\" value=\"OK\" id=\"okbutton\" name=\"GenReq\">\r\n" +  //$NON-NLS-1$
			"</form>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"</div>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<script type=\"text/javascript\">\r\n" +  //$NON-NLS-1$
			"<!--\r\n" +  //$NON-NLS-1$
			"	\r\n" +  //$NON-NLS-1$
			"	if (navigator.appName.indexOf(\"Explorer\") != -1) {\r\n" +  //$NON-NLS-1$
			"		document.getElementById('explorercode').style.visibility = 'visible';\r\n" +  //$NON-NLS-1$
			"	}\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"</script>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"<script type=\"text/vbscript\" defer=\"true\">\r\n" +  //$NON-NLS-1$
			"<!--\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"	If InStr(Navigator.UserAgent, \"Windows NT 6\") <> 0 Then\r\n" +  //$NON-NLS-1$
			"		Call GetCertEnrollCSPList()\r\n" +  //$NON-NLS-1$
			"		Document.CertReqForm.classid.value = \"clsid:884e2049-217d-11da-b2a4-000e7bbb2b09\"\r\n" +  //$NON-NLS-1$
			"	Else\r\n" +  //$NON-NLS-1$
			"		GetProviderList()\r\n" +  //$NON-NLS-1$
			"	End If\r\n" +  //$NON-NLS-1$
			"-->\r\n" +  //$NON-NLS-1$
			"</script>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			"  </body>\r\n" +  //$NON-NLS-1$
			"</html>\r\n" +  //$NON-NLS-1$
			"\r\n" +  //$NON-NLS-1$
			""; //$NON-NLS-1$
}
