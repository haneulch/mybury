import Common from '../common.js';
import './vue_filter.js';

new Vue({
    el: '#content',
    data() {
        return {
            content : []
        }
    },
    mounted() {
        var _vue = this;

        Common.get('/notice/json', function(data) {
            $.extend(_vue, {content: data});
        });
    },
    methods: {
        toDetail(seq) {
            location.href = '/notice/detail?seq=' + seq;
        }
    }
});
