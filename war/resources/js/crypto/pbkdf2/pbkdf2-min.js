/*
 * Crypto-JS v2.0.0
 * http://code.google.com/p/crypto-js/
 * Copyright (c) 2009, Jeff Mott. All rights reserved.
 * http://code.google.com/p/crypto-js/wiki/License
 */
(function(){var e=Crypto,a=e.util,b=e.charenc,d=b.UTF8,c=b.Binary;e.PBKDF2=function(q,o,f,t){if(q.constructor==String){q=d.stringToBytes(q)}if(o.constructor==String){o=d.stringToBytes(o)}var s=t&&t.hasher||e.SHA1,k=t&&t.iterations||1;function p(i,j){return e.HMAC(s,j,i,{asBytes:true})}var h=[],g=1;while(h.length<f){var l=p(q,o.concat(a.wordsToBytes([g])));for(var r=l,n=1;n<k;n++){r=p(q,r);for(var m=0;m<l.length;m++){l[m]^=r[m]}}h=h.concat(l);g++}h.length=f;return t&&t.asBytes?h:t&&t.asString?c.bytesToString(h):a.bytesToHex(h)}})();