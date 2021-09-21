package com.proprog.applicationtest.ui.main.adapter

import android.widget.TextView
import com.idanatz.oneadapter.external.modules.ItemModule
import com.proprog.applicationtest.R
import com.proprog.applicationtest.data.model.Item

class ItemsModule : ItemModule<Item>() {
    init {
        config {
            layoutResource = R.layout.stack_list_item
        }
        onBind { model, viewBinder, metadata ->
            val title = viewBinder.findViewById<TextView>(R.id.tvUserId)
            title.text = model.owner.displayName
        }
        onUnbind { model, viewBinder, metadata ->
            // unbind logic like stop animation, release webview resources, etc.
        }
    }
}