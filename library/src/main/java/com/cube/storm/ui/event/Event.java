package com.cube.storm.ui.event;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cube.storm.ui.macro.Macro;
import com.cube.storm.ui.model.Model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * // TODO: Add class description
 *
 * @author Callum Taylor
 * @project LightningUi
 */
@NoArgsConstructor @AllArgsConstructor(suppressConstructorProperties = true)
@Accessors(chain = true) @Data
public abstract class Event extends Model
{
	protected ArrayList<? extends Macro> macros = new ArrayList<>();

	public abstract void register(RecyclerView.Adapter adapter, Model model, View view);
}
