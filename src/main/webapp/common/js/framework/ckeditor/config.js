/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */
var Toolbars = [
	['Undo', 'Redo', '-', 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord'],
	['Image', 'Table', 'HorizontalRule',  'Smiley', 'SpecialChar', 'PageBreak'],
	['Maximize', 'ShowBlocks'],
	['Find', 'Replace', '-', 'SpellChecker', 'Scayt', 'Source', 'Link', 'Unlink'],
	'/',
	['Font', 'FontSize'],
	['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'TextColor', 'BGColor', '-', 'RemoveFormat'],
	['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl']

];

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	//config.font_defaultLabel = 'Gulim';
	//config.font_names = 'Gulim/Gulim;Dotum/Dotum;Batang/Batang;Gungsuh/Gungsuh;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';

	//config.height = 290;
	//config.pasteFromWordRemoveFontStyles = false; // 복사한 대상 문서를 붙여 넣을시 폰트/스타일 제거여부
	config.startupFocus = true;
	config.font_names = '굴림/굴림;궁서/궁서;돋움/돋움;바탕/바탕;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';
	config.fontSize_sizes = '8/8pt;9/9pt;10/10pt;11/11pt;12/12pt;14/14pt;16/16pt;18/18pt;20/20pt;22/22pt;24/24pt;26/26pt;28/28pt;36/36pt;48/48pt;72/72pt';
	config.fontSize_defaultLabel = '10';
	config.toolbar = Toolbars;

	//enterMode : '2',
	//shiftEnterMode : '1',
	//bodyClass : 'contents',
	//contentsCss : '/gwlib/comm/css/ckeditor.css',
	//font_defaultLabel : '굴림',
	//font_names : '굴림/gulim;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana',
};
