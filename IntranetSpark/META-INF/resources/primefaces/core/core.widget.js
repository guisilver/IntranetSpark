(function(){var a=false,b=/xyz/.test(function(){xyz})?/\b_super\b/:/.*/;this.Class=function(){};Class.extend=function(g){var f=this.prototype;a=true;var e=new this();a=false;for(var d in g){e[d]=typeof g[d]=="function"&&typeof f[d]=="function"&&b.test(g[d])?(function(h,i){return function(){var k=this._super;this._super=f[h];var j=i.apply(this,arguments);this._super=k;return j}})(d,g[d]):g[d]}function c(){if(!a&&this.init){this.init.apply(this,arguments)}}c.prototype=e;c.prototype.constructor=c;c.extend=arguments.callee;return c}})();PrimeFaces.widget={};PrimeFaces.widget.BaseWidget=Class.extend({init:function(a){this.cfg=a;this.id=a.id;this.jqId=PrimeFaces.escapeClientId(this.id);this.jq=$(this.jqId);this.widgetVar=a.widgetVar;$(this.jqId+"_s").remove();if(this.widgetVar){var b=this;this.jq.on("remove",function(){PrimeFaces.detachedWidgets.push(b.widgetVar)})}},refresh:function(a){return this.init(a)},destroy:function(){PrimeFaces.debug("Destroyed detached widget: "+this.widgetVar)},isDetached:function(){return document.getElementById(this.id)===null},getJQ:function(){return this.jq},removeScriptElement:function(a){$(PrimeFaces.escapeClientId(a)+"_s").remove()}});PrimeFaces.widget.DeferredWidget=PrimeFaces.widget.BaseWidget.extend({renderDeferred:function(){if(this.jq.is(":visible")){this._render()}else{var a=this.jq.closest(".ui-hidden-container"),b=this;if(a.length){this.addDeferredRender(this.id,a,function(){return b.render()})}}},render:function(){if(this.jq.is(":visible")){this._render();return true}else{return false}},_render:function(){throw"Unsupported Operation"},destroy:function(){this._super();PrimeFaces.removeDeferredRenders(this.id)},addDeferredRender:function(b,a,d){PrimeFaces.addDeferredRender(b,a.attr("id"),d);if(a.is(":hidden")){var c=this.jq.closest(".ui-hidden-container");if(c.length){this.addDeferredRender(b,a.parent().closest(".ui-hidden-container"),d)}}}});