/*
 * AET
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
define(['angularAMD'], function (angularAMD) {
  'use strict';
  // custom prefix for custom directive 'aet' see: http://bit.ly/29U2YFf
  angularAMD.directive('aetSidepanel',
      ['$rootScope', 'localStorageService', SidepanelDirective]);

  function SidepanelDirective($rootScope, localStorageService) {
    var EXPANDED_SIDEBAR_KEY_NAME = 'aet:expandedSidepanel';
    var INIT_SIDEPANEL_WIDTH = 350;
    var $sidepanel;
    var $content;
    var $toggleIcon;
    var $body;

    return {
      restrict: 'AE',
      scope: {
        'type': '@'
      },
      link: linkFunc
    };

    function linkFunc($scope, $element) {
      var storedExpanded = localStorageService.get(EXPANDED_SIDEBAR_KEY_NAME);
      var pageX = INIT_SIDEPANEL_WIDTH;
      var isSidepanelResized = false;

      if (storedExpanded === null) {
        expand();
      }

      $body = $element;

      $rootScope.sidebarExpanded = isExpanded();

      $rootScope.$on('$stateChangeSuccess', function() {
        $content = $element.find('.main');
        $sidepanel = $element.find('.aside');
        $toggleIcon = $element.find('.toolbar-toggle i');
      });

      $element.on('mousedown', '.aside-resizer', function (e) {
        isSidepanelResized = true;

        e.preventDefault();
      });

      $element.on('mousemove', function (e) {
        if (isSidepanelResized) {
          pageX = limitResize(e.pageX);
          updateWidth(pageX);
          e.preventDefault();
        }
      });

      $element.on('mouseup', function () {
        isSidepanelResized = false;
      });

      $element.on('click', '.toolbar-toggle', function () {
        $element.toggleClass('menu-expanded');
        toggleSidepanel();

        if (isExpanded()) {
          $toggleIcon.removeClass('glyphicon-chevron-right').addClass(
              'glyphicon-chevron-left');
        } else {
          $toggleIcon.removeClass('glyphicon-chevron-left').addClass(
              'glyphicon-chevron-right');
        }
      });
    }

    function isExpanded() {
      return localStorageService.get(EXPANDED_SIDEBAR_KEY_NAME);
    }

    function toggleSidepanel() {
      if (isExpanded()) {
        close();
      } else {
        expand();
      }
    }

    function expand() {
      $content.css('left', $sidepanel.outerWidth());
      $content.css('width', $body.width() - $sidepanel.outerWidth());
      $sidepanel.css('left', 0);

      localStorageService.put(EXPANDED_SIDEBAR_KEY_NAME, true);
    }

    function close() {
      $content.css('left', 0);
      $content.css('width', $body.width());
      $sidepanel.css('left', -$sidepanel.outerWidth());

      localStorageService.put(EXPANDED_SIDEBAR_KEY_NAME, false);
    }

    function updateWidth(newWidth) {
      var newContentWidth = $body.width() - newWidth;

      $content.css('left', newWidth);
      $content.css('width', newContentWidth);
      $sidepanel.css('width', newWidth);
    }

    function limitResize(xPos) {
      return Math.min(Math.max(INIT_SIDEPANEL_WIDTH, xPos), $body.width()/2);
    }
  }
});
