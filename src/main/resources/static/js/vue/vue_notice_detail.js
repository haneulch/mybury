import Common from '../common.js';
import './vue_filter.js';

new Vue({
  el: '#content',
  data() {
    return {
      notice: {}
    }
  },
  mounted() {
    var _vue = this;
    var obj = Common.getParam();

    Common.get(`/notice/json/${obj.seq}`, function (data) {
      data.isFaq = data.faqYn !== 'Y';
      $.extend(_vue, {notice: data});
    });
  },
  methods: {
    sendMail(title) {
      location.href = `mailto:mybury.info@gamil.com?subject=${title}`;
    }
  }
});
