/*
 * Crypto-JS v2.0.0
 * http://code.google.com/p/crypto-js/
 * Copyright (c) 2009, Jeff Mott. All rights reserved.
 * http://code.google.com/p/crypto-js/wiki/License
 */
(function(){var f=Crypto,a=f.util,b=f.charenc,e=b.UTF8,c=b.Binary;var d=f.MARC4={encrypt:function(l,j){var g=e.stringToBytes(l),i=a.randomBytes(16),h=j.constructor==String?f.PBKDF2(j,i,32,{asBytes:true}):j;d._marc4(g,h,1536);return a.bytesToBase64(i.concat(g))},decrypt:function(j,i){var l=a.base64ToBytes(j),h=l.splice(0,16),g=i.constructor==String?f.PBKDF2(i,h,32,{asBytes:true}):i;d._marc4(l,g,1536);return e.bytesToString(l)},_marc4:function(g,n,l){var p,o,q,h;for(p=0,q=[];p<256;p++){q[p]=p}for(p=0,o=0;p<256;p++){o=(o+q[p]+n[p%n.length])%256;h=q[p];q[p]=q[o];q[o]=h}p=o=0;for(var n=-l;n<g.length;n++){p=(p+1)%256;o=(o+q[p])%256;h=q[p];q[p]=q[o];q[o]=h;if(n<0){continue}g[n]^=q[(q[p]+q[o])%256]}}}})();