/* 
Copyright (c) 2021 Exopteron 

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.exopteron.exomagicmod;

import com.exopteron.exomagicmod.items.ItemSetup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExoCreativeTab extends ItemGroup {

    public ExoCreativeTab(int index, String id) {
        super(index, id);
        //TODO Auto-generated constructor stub
    }

    @Override
    public ItemStack createIcon() {
        // TODO Auto-generated method stub
        return new ItemStack(ItemSetup.MAGIC_WAND);
    }
    
}
