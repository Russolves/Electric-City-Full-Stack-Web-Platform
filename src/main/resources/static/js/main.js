
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).addClass('active');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).removeClass('active');
            showPass = 0;
        }
        
    });


})(jQuery);

// Second function
$(function() {

    var siteSticky = function() {
          $(".js-sticky-header").sticky({topSpacing:0});
      };
      siteSticky();
  
      var siteMenuClone = function() {
  
          $('.js-clone-nav').each(function() {
              var $this = $(this);
              $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
          });
  
  
          setTimeout(function() {
              
              var counter = 0;
        $('.site-mobile-menu .has-children').each(function(){
          var $this = $(this);
          
          $this.prepend('<span class="arrow-collapse collapsed">');
  
          $this.find('.arrow-collapse').attr({
            'data-toggle' : 'collapse',
            'data-target' : '#collapseItem' + counter,
          });
  
          $this.find('> ul').attr({
            'class' : 'collapse',
            'id' : 'collapseItem' + counter,
          });
  
          counter++;
  
        });
  
      }, 1000);
  
          $('body').on('click', '.arrow-collapse', function(e) {
        var $this = $(this);
        if ( $this.closest('li').find('.collapse').hasClass('show') ) {
          $this.removeClass('active');
        } else {
          $this.addClass('active');
        }
        e.preventDefault();  
        
      });
  
          $(window).resize(function() {
              var $this = $(this),
                  w = $this.width();
  
              if ( w > 768 ) {
                  if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
              }
          })
  
          $('body').on('click', '.js-menu-toggle', function(e) {
              var $this = $(this);
              e.preventDefault();
  
              if ( $('body').hasClass('offcanvas-menu') ) {
                  $('body').removeClass('offcanvas-menu');
                  $this.removeClass('active');
              } else {
                  $('body').addClass('offcanvas-menu');
                  $this.addClass('active');
              }
          }) 
  
          // click outisde offcanvas
          $(document).mouseup(function(e) {
          var container = $(".site-mobile-menu");
          if (!container.is(e.target) && container.has(e.target).length === 0) {
            if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
          }
          });
      }; 
      siteMenuClone();
  
  });
  
//   The carousel main.js files
$(function() {

    var siteSticky = function() {
          $(".js-sticky-header").sticky({topSpacing:0});
      };
      siteSticky();
  
      var siteMenuClone = function() {
  
          $('.js-clone-nav').each(function() {
              var $this = $(this);
              $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
          });
  
  
          setTimeout(function() {
              
              var counter = 0;
        $('.site-mobile-menu .has-children').each(function(){
          var $this = $(this);
          
          $this.prepend('<span class="arrow-collapse collapsed">');
  
          $this.find('.arrow-collapse').attr({
            'data-toggle' : 'collapse',
            'data-target' : '#collapseItem' + counter,
          });
  
          $this.find('> ul').attr({
            'class' : 'collapse',
            'id' : 'collapseItem' + counter,
          });
  
          counter++;
  
        });
  
      }, 1000);
  
          $('body').on('click', '.arrow-collapse', function(e) {
        var $this = $(this);
        if ( $this.closest('li').find('.collapse').hasClass('show') ) {
          $this.removeClass('active');
        } else {
          $this.addClass('active');
        }
        e.preventDefault();  
        
      });
  
          $(window).resize(function() {
              var $this = $(this),
                  w = $this.width();
  
              if ( w > 768 ) {
                  if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
              }
          })
  
          $('body').on('click', '.js-menu-toggle', function(e) {
              var $this = $(this);
              e.preventDefault();
  
              if ( $('body').hasClass('offcanvas-menu') ) {
                  $('body').removeClass('offcanvas-menu');
                  $this.removeClass('active');
              } else {
                  $('body').addClass('offcanvas-menu');
                  $this.addClass('active');
              }
          }) 
  
          // click outisde offcanvas
          $(document).mouseup(function(e) {
          var container = $(".site-mobile-menu");
          if (!container.is(e.target) && container.has(e.target).length === 0) {
            if ( $('body').hasClass('offcanvas-menu') ) {
                      $('body').removeClass('offcanvas-menu');
                  }
          }
          });
      }; 
      siteMenuClone();
  
  });