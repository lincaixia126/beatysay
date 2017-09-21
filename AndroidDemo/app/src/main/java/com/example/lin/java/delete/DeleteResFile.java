package com.example.lin.java.delete;

import java.io.File;

public class DeleteResFile {

    private String[] deleteFiles =
            {
                    "dc_buffet_msglist_tab_selector.xml",
                    "dc_detail_chapter_title.xml",
                    "dc_detail_fragment.xml",
                    "dc_detail_good_info_header.xml",
                    "dc_detail_header_overview.xml",
                    "dc_detail_info_payinfo.xml",
                    "dc_detail_info_payinfo_item.xml",
                    "dc_detail_overview_header_title.xml",
                    "dc_detail_overview_header_value.xml",
                    "dc_list_view.xml",
                    "dc_order_detail_fixed_header.xml"
            };

    public static void main(String[] args) {
        new DeleteResFile().delete();
    }

    private void delete() {

        String baseUrl = "/Users/lin/ASWorkspace/erp-pos-cashier/checkstand/src/main/res/layout/";
        for (String deleteFile : deleteFiles) {
            File f = new File(baseUrl + deleteFile);
            if (f.exists()) {
                f.delete();
                System.out.println("删除文件： " + deleteFile + "成功");
            } else {
                System.out.println("删除文件： " + deleteFile + "不成功");
            }
        }

    }
}
