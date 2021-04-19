/*
 * Crypto-JS v2.0.0
 * http://code.google.com/p/crypto-js/
 * Copyright (c) 2009, Jeff Mott. All rights reserved.
 * http://code.google.com/p/crypto-js/wiki/License
 */
(function(){var e=Crypto,a=e.util,b=e.charenc,d=b.UTF8,c=b.Binary;e.HMAC=function(l,m,k,h){if(m.constructor==String){m=d.stringToBytes(m)}if(k.constructor==String){k=d.stringToBytes(k)}if(k.length>l._blocksize*4){k=l(k,{asBytes:true})}var g=k.slice(0),n=k.slice(0);for(var j=0;j<l._blocksize*4;j++){g[j]^=92;n[j]^=54}var f=l(g.concat(l(n.concat(m),{asBytes:true})),{asBytes:true});return h&&h.asBytes?f:h&&h.asString?c.bytesToString(f):a.bytesToHex(f)}})();