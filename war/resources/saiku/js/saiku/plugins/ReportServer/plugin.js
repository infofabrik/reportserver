var RsSaikuWorkspace;

var ReportServerPlugin = {

	bind_callbacks : function(workspace) {
		RsSaikuWorkspace = workspace;

		// alert('edit query');
		// self.edit_query();

		/* add reload button */
		if (Settings.RS_SHOW_RESET) {
			var $chart_button = $(
					'<a href="#resetsaiku" class="button toolbar i18n resetsaiku" title="Reset Cube"></a>')
					.css(
							{
								'background-image' : "url('js/saiku/plugins/ReportServer/arrow_rotate_clockwise_red.png')",
								'background-repeat' : 'no-repeat',
								'background-position' : '5px 5px'
							}).click(
							function() {
								if (!Settings.RS_CONFIGURATION_PROTECTED) {
									$.get(Settings.REST_MOUNT_POINT + "api/"
											+ Saiku.session.username
											+ "/repository/resource/reset",
											function(result) {
												if (result) {
													location.reload();
												}
											});
								}
							});
			var $chart_li = $('<li class="seperator"></li>').append(
					$chart_button);

			workspace.bind('query:new', function() {
				$('.resetsaiku').removeClass('disabled_toolbar');
			});

			workspace.bind('query:result', function() {
				$('.resetsaiku').removeClass('disabled_toolbar');
			});

			/* allow rendering to take place */
			setTimeout(function() {
				$('.new').hide();

				$('.workspace_toolbar').find("ul").append($chart_li);
				$.get(
						Settings.REST_MOUNT_POINT + "api/"
								+ Saiku.session.username
								+ "/repository/resource/isMDX",
						function(result) {
							if (!result) {		
								$('.switch_to_mdx').hide();
								$('.switch_to_mdx').parent().hide();
								$('.ace_text-input').hide();
								$('.ace_text-input').parent().hide();
								$('.mdx').parent().hide();
							} else {
								$('.switch_to_mdx').show();
								$('.switch_to_mdx').parent().show();
								$('.ace_text-input').show();
								$('.ace_text-input').parent().show();
								$('.mdx').parent().show();
							}
						});
			}, 100);
		} else {
			setTimeout(function() {
				$('.new').click(
						function() {
							if (!Settings.RS_CONFIGURATION_PROTECTED) {
								$.get(Settings.REST_MOUNT_POINT + "api/"
										+ Saiku.session.username
										+ "/repository/resource/reset",
										function(result) {
											if (result) {
												location.reload();
											}
										});
							}
						});
			}, 100);
		}

		setTimeout(function() {
			$('.run').trigger('click');

		}, 110);
	}
};

Saiku.events.bind('session:new', function(session) {
	$('#header').remove();

	if (Saiku.tabs._tabs[0] && Saiku.tabs._tabs[0].content) {
		ReportServerPlugin.bind_callbacks(Saiku.tabs._tabs[0].content);
	}

	Saiku.session.bind('workspace:new', function(args) {
		ReportServerPlugin.bind_callbacks(args.workspace);
	});

	setTimeout(function() {
		if ($('.cubes').prop('selectedIndex') != 1) {
			$('.cubes').prop('selectedIndex', 1);
			$('.cubes').trigger('change');

			var $chart_li = $('<li class="seperator"></li>');
			$('.workspace_toolbar').find("ul").append($chart_li);
			
			if (Settings.RS_CONFIGURATION_PROTECTED) {
				setTimeout(function() {
					$('.edit').trigger('click');
					$('.edit').prop('disabled', true);
					$('.edit').addClass('disabled');
					$('.resetsaiku').prop('disabled', true);
					$('.resetsaiku').addClass('disabled');
					$('body').bind("contextmenu", function(e) {
						return false;
					});
				}, 300);
		} else {
			setTimeout(function() {
				$('.edit').show();
				$('.edit').prop('disabled', false);
				$('.edit').removeClass('disabled');
				$('.resetsaiku').prop('disabled', false);
				$('.resetsaiku').removeClass('disabled');
				$('body').bind("contextmenu", function(e) {
					return true;
				});
			}, 300);
		}
		}
	}, 100);
});
