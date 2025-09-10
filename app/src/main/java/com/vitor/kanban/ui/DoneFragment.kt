package com.vitor.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitor.kanban.data.model.Status
import com.vitor.kanban.databinding.FragmentDoneBinding
import com.vitor.kanban.data.model.Task
import com.vitor.kanban.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view, savedInstanceState)

        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext(),){ task, option -> optionSelected(task,option)}
        binding.recyclerviewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewTask.setHasFixedSize(true)
        binding.recyclerviewTask.adapter = taskAdapter
    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Movendo para done: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Movendo para A to-do: ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getTask() {
        val taskList = listOf(
            Task(
                id = "20",
                description = "Prototipar as telas web",
                status = Status.DONE
            ),
            Task(
                id = "21",
                description = "Inclusão com o banco de dados(firebase)",
                status = Status.DONE
            ),
            Task(
                id = "22",
                description = "aplicar api de localização",
                status = Status.DONE
            ),
            Task(
                id = "23",
                description = "relatorizar para o scrummaster",
                status = Status.DONE
            ),
        )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}