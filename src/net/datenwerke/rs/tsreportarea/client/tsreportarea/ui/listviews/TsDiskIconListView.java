package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews;

import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteListViewHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragCancelEvent;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.ListViewDragSource;
import com.sencha.gxt.dnd.core.client.ListViewDropTarget;
import com.sencha.gxt.theme.base.client.listview.ListViewCustomAppearance;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

public class TsDiskIconListView implements TsFavoriteListViewHook {
	
	private TsDiskUIService tsDiskUIService;
	protected int doubleClick;

	@Inject
	public TsDiskIconListView(TsDiskUIService tsDiskUIService) {
		this.tsDiskUIService = tsDiskUIService;
	}

	public class TemplateModel {
		private ImageResource icon;
		private String name;

		public SafeHtml getIcon() {
			if(icon instanceof CssIconImageResource)
				return ((CssIconImageResource)icon).getCssIcon(2);
			return AbstractImagePrototype.create(icon).getSafeHtml();
		}
		public void setIcon(ImageResource icon) {
			this.icon = icon;
		}
		public void setIcon(SafeHtml icon){

		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public TemplateModel(AbstractTsDiskNodeDto node) {
			if(node instanceof TsDiskFolderDto){
				this.name = ((TsDiskFolderDto) node).getName();
			}else if(node instanceof TsDiskGeneralReferenceDto){
				this.name = ((TsDiskGeneralReferenceDto) node).getName();
			}

			this.icon = tsDiskUIService.getIconFor(node, true);
		}
	}

	interface Renderer extends XTemplates {
		@XTemplate("<div class=\"{style.thumb} rs-teamspace-lv-l-icon\">{model.icon}</div><span>{model.name}</span>")
		SafeHtml render(TemplateModel model, Style style);
	}

	interface Resources extends ClientBundle {
		@Source("TsDiskIconListView.gss")
		Style css();
	}

	interface Style extends CssResource {
		String over();
		String select();
		String thumb();
		String thumbWrap();
	}

	@Override
	public ImageResource getViewIcon() {
		return BaseIcon.ITEMS_LARGE.toImageResource();
	}

	@Override
	public TsDiskListView getListView(final TsDiskMainComponent mainComponent) {
		final Resources resources = GWT.create(Resources.class);
		resources.css().ensureInjected();
		final Style style = resources.css();
		final Renderer r = GWT.create(Renderer.class);

		ListViewCustomAppearance<AbstractTsDiskNodeDto> appearance = 
				new ListViewCustomAppearance<AbstractTsDiskNodeDto>("."+style.thumbWrap(), style.over() + " rs-teamspace-lv-over", style.select()) {
			@Override
			public void renderEnd(SafeHtmlBuilder builder) {
				String markup = new StringBuilder("<div class=\"").append(CommonStyles.get().clear()).append("\"></div>").toString();
				builder.appendHtmlConstant(markup);
			}
			
			@Override
			public void render(SafeHtmlBuilder builder) {
				builder.appendHtmlConstant("<div class='rs-lview'></div>");
			}

			@Override
			public void renderItem(SafeHtmlBuilder builder, SafeHtml content) {
				builder.appendHtmlConstant("<div class=\"" + style.thumbWrap() + "\">");
				builder.append(content);
				builder.appendHtmlConstant("</div>");
			}
		};

		final ListView<AbstractTsDiskNodeDto, AbstractTsDiskNodeDto> listView =	new ListView<AbstractTsDiskNodeDto, AbstractTsDiskNodeDto>(
																							mainComponent.getListStore(), 
																							new IdentityValueProvider<AbstractTsDiskNodeDto>(), 
																							appearance);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
		new ExtendedKeyNav(listView){
			@Override
			protected void onSelectAll() {
				listView.getSelectionModel().selectAll();
			};
			@Override
			public void onDelete(NativeEvent evt) {
			}
		};
		
		
		listView.addHandler(new DoubleClickHandler(){

			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				NativeEvent ne = event.getNativeEvent().cast();

				com.google.gwt.dom.client.Element row = listView.findElement((Element) ne.getEventTarget().cast());

				if (row != null) {
					int idx = listView.findElementIndex(row);
					AbstractTsDiskNodeDto item = listView.getStore().get(idx);
					if(null != item){
						doubleClick++;
						
						mainComponent.itemOpened(item);
					
						DelayedTask task = new DelayedTask() {
							@Override
							public void onExecute() {
								doubleClick--;
								if(doubleClick < 0)
									doubleClick = 0;
							}
						};
						
						task.delay(500);
					}
				}


			}
		}, DoubleClickEvent.getType());
		
		listView.setCell(new SimpleSafeHtmlCell<AbstractTsDiskNodeDto>(new AbstractSafeHtmlRenderer<AbstractTsDiskNodeDto>(){
			@Override
			public SafeHtml render(AbstractTsDiskNodeDto object) {
				return r.render(new TemplateModel(object), style);
			}
		}));

		mainComponent.setMenuChanger(listView,new ItemSelector() {
			@Override
			public List<AbstractTsDiskNodeDto> getSelectedItems() {
				return listView.getSelectionModel().getSelectedItems();
			}
			@Override
			public boolean isInFolder() {
				return true;
			}
		});

		listView.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<AbstractTsDiskNodeDto>() {
			@Override
			public void onSelectionChanged(
					SelectionChangedEvent<AbstractTsDiskNodeDto> event) {
				if(event.getSelection().isEmpty())
					return;
				
				final AbstractTsDiskNodeDto item = event.getSelection().get(0);
				DelayedTask task = new DelayedTask() {
					
					@Override
					public void onExecute() {
						if(! mainComponent.isInDrag() && doubleClick <= 0)
							mainComponent.itemSelected(item);
						else
							listView.getSelectionModel().deselectAll();
					}
				};
				
				if(null != item)
					task.delay(200);
				else
					task.cancel();
			}
		});
		
		if(mainComponent.allowDrag()){
			ListViewDragSource<AbstractTsDiskNodeDto> dragSource = new ListViewDragSource<AbstractTsDiskNodeDto>(listView){

				@Override
				protected void onDragStart(DndDragStartEvent event) {
					super.onDragStart(event);
					
					/* make sure something is really dragged ..
					 * for some reason a drag operation is started (but not stopped)
					 * when changing column sizes
					 */
					Object data = event.getData();
					if(null != data && data instanceof List && 
						! ((List)data).isEmpty() && 
						((List)data).get(0) instanceof AbstractTsDiskNodeDto){
						mainComponent.setInDrag(true);
					}
				}
				
				@Override
				protected void onDragCancelled(DndDragCancelEvent event) {
					super.onDragCancelled(event);
					mainComponent.setInDrag(false);
				}
				
				@Override
				protected void onDragDrop(DndDropEvent event) {
					/* do not call super class .. remove item from store in drop target */
					mainComponent.setInDrag(false);
				}
				
				@Override
				protected void onDragFail(DndDropEvent event) {
					super.onDragFail(event);
					mainComponent.setInDrag(false);
				}
			};
			dragSource.setGroup(TsDiskMainComponent.FAVORITE_ITEM_DRAG_GROUP);
			
			ListViewDropTarget<AbstractTsDiskNodeDto> dropTarget = new ListViewDropTarget<AbstractTsDiskNodeDto>(listView){
				
				@Override
				protected void onDragDrop(DndDropEvent e) {
					/* find item */
					AbstractTsDiskNodeDto dropOnItem = getDropOnItem(e.getDragEndEvent().getNativeEvent().getEventTarget().<Element> cast());
				    if(null != dropOnItem){
				    	AbstractTsDiskNodeDto parent = (AbstractTsDiskNodeDto)dropOnItem;
					   
				    	if(parent instanceof TsDiskFolderDto){
				    		mainComponent.moveNodes((List)e.getData(), parent);
					    }
				    } else {
						e.getStatusProxy().setStatus(false);
				    }
				}
				
				/**
				 * Only allow drops on folders
				 */
				@Override
				protected void onDragMove(com.sencha.gxt.dnd.core.client.DndDragMoveEvent e) {
					super.onDragMove(e);
					if(e.isCancelled())
						return;
					
					if(! testEvent(e.getDragMoveEvent().getNativeEvent().getEventTarget().<Element> cast(), e.getDragSource().getData())){
						e.setCancelled(true);
						e.getStatusProxy().setStatus(false);
					}
				}
				
				@Override
				protected void onDragEnter(DndDragEnterEvent e) {
					super.onDragEnter(e);
					if(e.isCancelled())
						return;
					
					if(! testEvent(e.getDragEnterEvent().getNativeEvent().getEventTarget().<Element> cast(), e.getDragSource().getData())){
						e.setCancelled(true);
						e.getStatusProxy().setStatus(false);
					}
				}
	
				protected boolean testEvent(com.google.gwt.dom.client.Element element, Object data) {
					/* find item */
					AbstractTsDiskNodeDto dropOnItem = getDropOnItem(element);

					boolean allow = false;
					if(null != dropOnItem){
						boolean dropOnSelf = false; 
						List<Object> models = prepareDropData(data, true);
						for(Object model : models){
							if(dropOnItem.equals(model)){
								dropOnSelf = true;
								break;
							}
						}

						if(! dropOnSelf){
							if(dropOnItem instanceof TsDiskFolderDto)
								allow = true;
						}
					}
					return allow;
				}

				protected AbstractTsDiskNodeDto getDropOnItem(com.google.gwt.dom.client.Element el) {
					com.google.gwt.dom.client.Element row = listView.findElement(el);

					if (row == null && listView.getStore().size() > 0) {
						row = listView.getElement(listView.getStore().size() - 1).cast();
					}

					if (row != null) {
						int idx = listView.findElementIndex(row);
						return listView.getStore().get(idx);
					}

					return null;
				}
				
			};
			dropTarget.setAllowSelfAsSource(true);
			dropTarget.setGroup(TsDiskMainComponent.FAVORITE_ITEM_DRAG_GROUP);
			dropTarget.setFeedback(Feedback.APPEND);
		}


		return new TsDiskListView() {

			@Override
			public void select(AbstractTsDiskNodeDto model) {
				listView.getSelectionModel().select(model, false);
			}

			@Override
			public List<AbstractTsDiskNodeDto> getSelected() {
				return listView.getSelectionModel().getSelectedItems();
			}

			@Override
			public Widget getComponent() {
				return listView;
			}
			
		};
	}

	@Override
	public String getViewId() {
		return "iconlistview";
	}

}
