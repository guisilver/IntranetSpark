(function($) {
    var cultures = $.cultures,
        en = cultures.en,
        standard = en.calendars.standard,
        culture = cultures["mn-MN"] = $.extend(true, {}, en, {
        name: "mn-MN",
        englishName: "Mongolian (Cyrillic, Mongolia)",
        nativeName: "Монгол хэл (Монгол улс)",
        language: "mn-Cyrl",
        numberFormat: {
            ',': " ",
            '.': ",",
            percent: {
                ',': " ",
                '.': ","
            },
            currency: {
                pattern: ["-n$","n$"],
                ',': " ",
                '.': ",",
                symbol: "₮"
            }
        },
        calendars: {
            standard: $.extend(true, {}, standard, {
                '/': ".",
                firstDay: 1,
                days: {
                    names: ["Ням","Даваа","Мягмар","Лхагва","Пүрэв","Баасан","Бямба"],
                    namesAbbr: ["Ня","Да","Мя","Лх","Пү","Ба","Бя"],
                    namesShort: ["Ня","Да","Мя","Лх","Пү","Ба","Бя"]
                },
                months: {
                    names: ["1 дүгээр сар","2 дугаар сар","3 дугаар сар","4 дүгээр сар","5 дугаар сар","6 дугаар сар","7 дугаар сар","8 дугаар сар","9 дүгээр сар","10 дугаар сар","11 дүгээр сар","12 дугаар сар",""],
                    namesAbbr: ["I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII",""]
                },
                monthsGenitive: {
                    names: ["1 дүгээр сарын","2 дугаар сарын","3 дугаар сарын","4 дүгээр сарын","5 дугаар сарын","6 дугаар сарын","7 дугаар сарын","8 дугаар сарын","9 дүгээр сарын","10 дугаар сарын","11 дүгээр сарын","12 дугаар сарын",""],
                    namesAbbr: ["I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII",""]
                },
                AM: null,
                PM: null,
                patterns: {
                    d: "yy.MM.dd",
                    D: "yyyy 'оны' MMMM d",
                    t: "H:mm",
                    T: "H:mm:ss",
                    f: "yyyy 'оны' MMMM d H:mm",
                    F: "yyyy 'оны' MMMM d H:mm:ss",
                    M: "d MMMM",
                    Y: "yyyy 'он' MMMM"
                }
            })
        }
    }, cultures["mn-MN"]);
    culture.calendar = culture.calendars.standard;
})(jQuery);