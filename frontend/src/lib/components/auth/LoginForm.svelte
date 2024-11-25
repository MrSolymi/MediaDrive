<script lang="ts">
	import * as Form from '$lib/components/ui/form';
	import { superForm, type Infer, type SuperValidated } from 'sveltekit-superforms';
	import { zodClient } from 'sveltekit-superforms/adapters';

	import { Input } from '$lib/components/ui/input/index.js';
	import { loginSchema, type LoginSchema } from '$lib/schema';

	export let data: SuperValidated<Infer<LoginSchema>>;

	const form = superForm(data, {
		validators: zodClient(loginSchema)
	});

	const { form: formData, enhance } = form;
</script>

<form method="POST" use:enhance>
	<div class="grid gap-4">
		<Form.Field class="grid gap-2" {form} name="username">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Username</Form.Label>
					<Input placeholder="username" {...props} bind:value={$formData.username} />
				{/snippet}
			</Form.Control>
		</Form.Field>
		<Form.Field class="grid gap-2" {form} name="password">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Password</Form.Label>
					<Input
						type="password"
						placeholder="password"
						{...props}
						bind:value={$formData.password}
					/>
				{/snippet}
			</Form.Control>
			<Form.Button type="submit" class="w-full">Login</Form.Button>
		</Form.Field>
	</div>
</form>
