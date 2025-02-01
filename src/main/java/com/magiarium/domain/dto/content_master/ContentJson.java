package com.magiarium.domain.dto.content_master;

import java.util.Map;

public class ContentJson {

    Integer index; // 何番目の要素か
    String value; // コンテンツ本体 →　主にテキスト要素想定
    Map<String, String> options; // コンテンツによって、関数の引数に渡すようのオプション要素

}
